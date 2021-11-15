package com.wenky.starter.custom.basic.operator;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-11 16:39
 */
public class XorOperator {

  // 异或 相同得0，不同得1
  public static void main(String[] args) {
    // 1 ^ 10 = 11 => 3
    System.out.println(1 ^ 2);
    // 0
    System.out.println(1 ^ 1);
    // 1
    System.out.println(0 ^ 1);
  }
}
