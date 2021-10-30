package com.wenky.starter.custom.aspect.example.execution;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 14:07
 */
@Aspect
@Component
public class ExecutionAspect {

  // 接口内的方法
  @Pointcut(
      "execution(* com.wenky.starter.custom.aspect.example.execution.service.ExecutionService.*(..))")
  private void execute() {}

  @Before("execute()")
  public void beforeExecute() {
    System.out.println("execute");
  }

  // 实现接口的子类(包含接口方法和子类自定义的方法)
  @Pointcut(
      "execution(* com.wenky.starter.custom.aspect.example.execution.service.ExecutionService+.*(..))")
  private void executePlus() {}

  @Before("executePlus()")
  public void beforeExecutePlus() {
    System.out.println("executePlus");
  }

  // 实现接口的子类方法同时抛出指定异常
  // 满足该条件的方法同时满足executePlus()，所以也会执行上面的
  @Pointcut(
      "execution(* com.wenky.starter.custom.aspect.example.execution.service.ExecutionService+.*(..) throws IndexOutOfBoundsException)")
  private void executeThrows() {}

  @Before("executeThrows()")
  public void beforeExecuteThrows() {
    System.out.println("executeThrows");
  }

  // 同时实现两个接口，需要HandleService+才能生效
  @Pointcut(
      "execution(* (com.wenky.starter.custom.aspect.example.execution.service.ExecutionService+ && java.io.Serializable+).*(..))")
  private void executeSerializable() {}

  @Before("executeSerializable()")
  public void beforeExecuteSerializable() {
    System.out.println("executeSerializable");
  }

  // 有指定注解的所有方法
  // 同@annotation(com.wenky.starter.custom.aspect.example.execution.annotation.Target)
  @Pointcut(
      "execution(@com.wenky.starter.custom.aspect.example.execution.annotation.Target * *(..))")
  private void executeMethodAnnotation() {}

  @Before("executeMethodAnnotation()")
  public void beforeExecuteMethodAnnotation() {
    System.out.println("executeMethodAnnotation");
  }

  // 有指定注解类的所有方法
  @Pointcut(
      "execution(* (@com.wenky.starter.custom.aspect.example.execution.annotation.Target *).*(..))")
  private void executeClassAnnotation() {}

  @Before("executeClassAnnotation()")
  public void beforeExecuteClassAnnotation() {
    System.out.println("executeClassAnnotation");
  }

  // 同时两个参数有指定注解的方法
  @Pointcut(
      "execution(* *(@com.wenky.starter.custom.aspect.example.execution.annotation.Target (*), @com.wenky.starter.custom.aspect.example.execution.annotation.Target (*)))")
  private void executeParamAnnotation() {}

  @Before("executeParamAnnotation()")
  public void beforeExecuteParamAnnotation() {
    System.out.println("executeParamAnnotation");
  }

  // 仅有一个参数且参数类上带有指定注解的方法
  // 同execution(* *((@com.wenky.starter.custom.aspect.example.execution.annotation.Target *)))
  @Pointcut(
      "execution(* *(@com.wenky.starter.custom.aspect.example.execution.annotation.Target *))")
  private void executeParamWithAnnotation() {}

  @Before("executeParamWithAnnotation()")
  public void beforeExecuteParamWithAnnotation() {
    System.out.println("executeParamWithAnnotation");
  }
}
