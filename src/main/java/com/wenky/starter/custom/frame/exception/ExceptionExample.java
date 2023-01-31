package com.wenky.starter.custom.frame.exception;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-06-22 12:25
 */
public class ExceptionExample {

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() throws Exception {
        try {
            throw new Exception("a");
        } catch (Exception e) {
            throw new Exception("b", e);
        }
    }
}
