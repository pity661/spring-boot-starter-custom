package com.wenky.starter.custom.controller.support;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 18:45
 */
public class VerifyException extends RuntimeException {
  public VerifyException() {
    super();
  }

  public VerifyException(String message) {
    super(message);
  }
}
