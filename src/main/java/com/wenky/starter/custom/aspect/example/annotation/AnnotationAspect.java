package com.wenky.starter.custom.aspect.example.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 15:46
 */
@Aspect
@Component
public class AnnotationAspect {

    // 切点 带有Target注解的方法
    @Pointcut("@annotation(com.wenky.starter.custom.aspect.example.annotation.Target)")
    private void executeMethod() {}

    // aspect::param JoinPoint 使用before、after、afterReturning、afterThrowing时，记录数据
    @Before("executeMethod()")
    public void beforeExecuteMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.getDeclaringTypeName());
        System.out.println(signature.getName());
        System.out.println("executeMethod");
    }

    // 带有targetA且有targetB注解的方法
    @Pointcut(
            "@annotation(com.wenky.starter.custom.aspect.example.annotation.TargetA) "
                    + "&& @annotation(com.wenky.starter.custom.aspect.example.annotation.TargetB)")
    private void executeDouble() {}

    @Before("executeDouble()")
    public void beforeExecuteDouble() {
        System.out.println("executeDouble");
    }

    // 同时持有targetA和targetB注解的所有方法
    @Pointcut(
            "execution(@com.wenky.starter.custom.aspect.example.annotation.TargetA @com.wenky.starter.custom.aspect.example.annotation.TargetB * *(..))")
    private void executeDouble1() {}

    @Before("executeDouble1()")
    public void beforeExecuteDouble1() {
        System.out.println("executeDouble1");
    }
}
