package com.wenky.starter.custom.frame.SPI;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-02-09 16:48
 */
public class Dog implements Animal {
    @Override
    public void name() {
        System.out.println("dog");
    }
}
