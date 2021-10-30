package com.wenky.starter.custom.aspect.example.common.bean;

import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-08 15:49
 */
@Component
public class CommonBean {

  public void handle() {
    System.out.println("handle");
  }
}
