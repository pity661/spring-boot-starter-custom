package com.wenky.starter.custom.aspect.example.common.bean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-02 11:40
 */
public class Boy extends Man {
  @Override
  public void jump() {
    System.out.println("Boy jump.");
  }
}
