package com.wenky.starter.custom.aspect.example.bean;

import com.wenky.starter.custom.aspect.example.annotation.A1;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-02 11:39
 */
@A1
public class Human implements Animal {
  public void say(String sentence) {
    System.out.println("Human says:" + sentence);
  }

  public void run() {
    System.out.println("Human runs.");
  }

  public void jump() {
    System.out.println("Human jump.");
  }
}
