package com.wenky.starter.custom.aspect.controller;

import com.wenky.starter.custom.util.GsonUtils;
import com.wenky.starter.custom.util.LoggerUtils;
import java.lang.reflect.Parameter;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 11:33
 */
@Aspect
@ConditionalOnProperty(
        prefix = "wenky.aspect",
        value = "controller",
        havingValue = "enable",
        matchIfMissing = false)
@Order(Integer.MIN_VALUE) // 指定最优先执行
public class ControllerAspect {

    @Pointcut("bean(*Controller)")
    public void execute() {}

    @Around("execute()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        // 打印请求信息
        LoggerUtils.info(
                String.format(
                        "【接口拦截】请求地址:【%s】,请求参数:【%s】",
                        request.getRequestURI(), getRequestParams(joinPoint)));
        StopWatch stopWatch = new StopWatch();
        Object result = null;
        Exception exception = null;
        try {
            stopWatch.start();
            result = joinPoint.proceed();
        } catch (Exception e) {
            exception = e;
        } finally {
            stopWatch.stop();
            LoggerUtils.info(
                    String.format(
                            "【接口拦截】请求地址:【%s】,请求耗时:【%s】ms,响应结果:【%s】",
                            request.getRequestURI(),
                            stopWatch.getTotalTimeMillis(),
                            GsonUtils.toString(result)));
            if (exception != null) {
                throw exception;
            }
        }
        return result;
    }

    private String getRequestParams(ProceedingJoinPoint joinPoint) {
        //        StringBuilder stringBuilder = new StringBuilder();
        Parameter[] parameters =
                ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] params = joinPoint.getArgs();
        if (params.length == 0) {
            return null;
        }
        // 没有请求参数的情况 通常默认参数只会有一个
        return params[0] instanceof javax.servlet.http.HttpServletRequest
                ? null
                : GsonUtils.toString(params[0]);
    }
}
