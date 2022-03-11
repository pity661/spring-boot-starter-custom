package com.wenky.starter.custom.aspect.example.annotation;

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

    @Pointcut("@annotation(com.wenky.starter.custom.aspect.example.annotation.Target)")
    private void executeMethod() {}

    @Before("executeMethod()")
    public void beforeExecuteMethod() {
        System.out.println("executeMethod");
    }

    @Pointcut(
            "@annotation(com.wenky.starter.custom.aspect.example.annotation.TargetA) && @annotation(com.wenky.starter.custom.aspect.example.annotation.TargetB)")
    private void executeDouble() {}

    @Before("executeDouble()")
    public void beforeExecuteDouble() {
        System.out.println("executeDouble");
    }

    @Pointcut(
            "execution(@com.wenky.starter.custom.aspect.example.annotation.TargetA @com.wenky.starter.custom.aspect.example.annotation.TargetB * *(..))")
    private void executeDouble1() {}

    @Before("executeDouble1()")
    public void beforeExecuteDouble1() {
        System.out.println("executeDouble1");
    }
}
