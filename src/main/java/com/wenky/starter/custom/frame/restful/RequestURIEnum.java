package com.wenky.starter.custom.frame.restful;

import org.springframework.http.HttpStatus;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-15 17:09
 */
public enum RequestURIEnum {
  OK(HttpStatus.OK),
  CREATED(HttpStatus.CREATED),
  FOUND(HttpStatus.FOUND),
  BAD_REQUEST(HttpStatus.BAD_REQUEST),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
  PAYMENT_REQUIRED(HttpStatus.PAYMENT_REQUIRED),
  NOT_FOUND(HttpStatus.NOT_FOUND),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR),
  ;
  private HttpStatus httpStatus;

  RequestURIEnum(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Integer getRelativeUrl() {
    return httpStatus.value();
  }
}
