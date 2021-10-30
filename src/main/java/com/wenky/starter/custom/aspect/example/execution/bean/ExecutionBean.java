package com.wenky.starter.custom.aspect.example.execution.bean;

import com.wenky.starter.custom.aspect.example.execution.annotation.Target;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 17:42
 */
@Target
@Component
public class ExecutionBean {

  public void handle() {
    System.out.println("handle");
  }

  public void handle(@Target String a, @Target String b) {
    System.out.println(String.format("handle, a:[%s], b:[%s]", a, b));
  }

  public void handle(Param param) {
    System.out.println("handle");
  }

  @Target
  public static class Param {}

  public void handle(Param1 param) {
    System.out.println("handle");
  }

  public static class Param1 {}
}
