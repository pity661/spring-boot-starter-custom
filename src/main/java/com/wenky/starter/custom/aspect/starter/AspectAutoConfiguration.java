package com.wenky.starter.custom.aspect.starter;

import com.wenky.starter.custom.util.LoggerUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2023-02-01 15:47
 */
@ConditionalOnProperty(prefix = "wenky.aspect.custom", value = "controller", havingValue = "enable")
@EnableConfigurationProperties(AspectCustomProperties.class)
public class AspectAutoConfiguration implements BeanFactoryAware {

    public AspectAutoConfiguration(AspectCustomProperties aspectCustomProperties) {
        LoggerUtils.construct();
        this.properties = aspectCustomProperties;
    }

    private final AspectCustomProperties properties;
    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean(name = "customAspect")
    public DefaultBeanFactoryPointcutAdvisor customAspect() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(properties.getPointcut());
        pointcut.setBeanFactory(beanFactory);

        DefaultBeanFactoryPointcutAdvisor advisor = new DefaultBeanFactoryPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setBeanFactory(beanFactory);
        MethodInterceptor methodInterceptor = this::doAround;
        advisor.setAdvice(methodInterceptor);
        return advisor;
    }

    private Object doAround(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        LoggerUtils.info(String.format("classType: %s", method.getDeclaringClass().getName()));
        LoggerUtils.info(String.format("method: %s", method.getName()));
        LoggerUtils.info(String.format("returnType: %s", method.getReturnType().getName()));
        Parameter[] parameters = method.getParameters();
        Object[] args = invocation.getArguments();
        LoggerUtils.info(
                Stream.of(parameters).map(Parameter::getName).collect(Collectors.toList()));
        LoggerUtils.info(Arrays.asList(args));
        try {
            Object result = invocation.proceed();
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
