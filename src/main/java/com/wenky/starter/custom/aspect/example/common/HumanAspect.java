package com.wenky.starter.custom.aspect.example.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-02 11:42
 */
@Aspect
@Component
public class HumanAspect {

    @Before("execution(* com.wenky.starter.custom.controller..*(..))")
    // 所有controller包及子包下面的所有方法的所有参数
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("【前置增强】" + methodName);
    }

    @Pointcut("execution(* com.wenky.starter.custom.aspect.example.common.bean..*(..))")
    private void execute() {}

    @Before(
            "@within(com.wenky.starter.custom.aspect.example.common.annotation.A1) && execution(* com.wenky.starter.custom.aspect.example.common.bean..*(..))")
    public void execute1() {
        System.out.println("@within(com.wenky.starter.custom.aspect.example.common.annotation.A1)");
    }

    @Before(
            "@target(com.wenky.starter.custom.aspect.example.common.annotation.A1) && execution(* com.wenky.starter.custom.aspect.example.common.bean..*(..))")
    public void execute2() {
        System.out.println("@target(com.wenky.starter.custom.aspect.example.common.annotation.A1)");
    }

    @Before(
            "@within(com.wenky.starter.custom.aspect.example.common.annotation.A2) && execution(* com.wenky.starter.custom.aspect..*(..))")
    public void execute3() {
        System.out.println("@within(com.wenky.starter.custom.aspect.example.common.annotation.A2)");
    }

    @Before(
            "@target(com.wenky.starter.custom.aspect.example.common.annotation.A2) && execution(* com.wenky.starter.custom.aspect..*(..))")
    public void execute4() {
        System.out.println("@target(com.wenky.starter.custom.aspect.example.common.annotation.A2)");
    }

    @Before("this(com.wenky.starter.custom.aspect.example.common.bean.Human)")
    public void execute5() {
        System.out.println("this(com.wenky.starter.custom.aspect.example.common.bean.Animal)");
    }

    @Before("target(com.wenky.starter.custom.aspect.example.common.bean.Human)")
    public void execute6() {
        System.out.println("target(com.wenky.starter.custom.aspect.example.common.bean.Animal)");
    }

    @Before("within(com.wenky.starter.custom.aspect.example.common.bean.Animal)")
    public void execute7() {
        System.out.println("within(com.wenky.starter.custom.aspect.example.common.bean.Animal)");
    }
}
