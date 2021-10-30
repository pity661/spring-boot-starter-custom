package com.wenky.starter.custom.aspect.example.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-08 15:28
 */
@Aspect
@Component
public class CommonAspect {
  @Pointcut("this(com.wenky.starter.custom.aspect.example.common.service.CommonService)")
  private void thisServicePointcut() {}

  @Before("thisServicePointcut()")
  public void beforeThisServicePointcut() {
    System.out.println("thisServicePointcut");
  }

  @Pointcut("target(com.wenky.starter.custom.aspect.example.common.service.CommonService)")
  private void targetServicePointcut() {}

  @Before("targetServicePointcut()")
  public void beforeTargetServicePointcut() {
    System.out.println("targetServicePointcut");
  }

  @Pointcut("this(com.wenky.starter.custom.aspect.example.common.service.impl.CommonServiceImpl)")
  private void thisServiceImplPointcut() {}

  @Before("thisServiceImplPointcut()")
  public void beforeThisServiceImplPointcut() {
    System.out.println("thisServiceImplPointcut");
  }

  @Pointcut("target(com.wenky.starter.custom.aspect.example.common.service.impl.CommonServiceImpl)")
  private void targetServiceImplPointcut() {}

  @Before("targetServiceImplPointcut()")
  public void beforeTargetServiceImplPointcut() {
    System.out.println("targetServiceImplPointcut");
  }

  @Pointcut("this(com.wenky.starter.custom.aspect.example.common.bean.CommonBean)")
  private void thisBeanPointcut() {}

  @Before("thisBeanPointcut()")
  public void beforeThisBeanPointcut() {
    System.out.println("thisBeanPointcut");
  }

  @Pointcut("target(com.wenky.starter.custom.aspect.example.common.bean.CommonBean)")
  private void targetBeanPointcut() {}

  @Before("targetBeanPointcut()")
  public void beforeTargetBeanPointcut() {
    System.out.println("targetBeanPointcut");
  }
}
