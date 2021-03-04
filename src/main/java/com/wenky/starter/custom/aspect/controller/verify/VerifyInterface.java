package com.wenky.starter.custom.aspect.controller.verify;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 15:36
 */
public interface VerifyInterface {
  // 参数校验需要实现该方法
  // 在controller使用该类子类，方法会自动参与参数校验逻辑
  boolean verify();
}
