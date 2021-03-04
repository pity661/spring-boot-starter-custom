package com.wenky.starter.custom.controller.support;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-07 16:00
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<String> exceptionHandler(Exception e) {
    LoggerUtils.exception("GlobalDefaultException", e);
    return ResponseEntity.ok("GlobalDefaultException");
  }

  @ExceptionHandler(VerifyException.class)
  @ResponseBody
  public ResponseEntity<String> verifyExceptionHandler(VerifyException e) {
    LoggerUtils.exception(LoggerUtils.getBizAction(), e);
    return ResponseEntity.ok(e.getMessage());
  }
}
