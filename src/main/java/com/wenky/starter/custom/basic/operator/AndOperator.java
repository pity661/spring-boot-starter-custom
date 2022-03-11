package com.wenky.starter.custom.basic.operator;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-04 18:26
 */
public class AndOperator {
    public static void main(String[] args) {
        andOperatorTest();
    }

    private static void andOperatorTest() {
        int a, b = 0, c, d;

        // 只要前面一个是false就不会执行后面的判断
        if ((a = 0) != 0 && (b = 2) != 0) {
            System.out.println("a,b");
        }
        // a:[0], b:[0]
        System.out.println(String.format("a:[%d], b:[%d]", a, b));

        // 前面一个是false也会执行后面的判断
        if ((c = 0) != 0 & (d = 2) != 0) {
            System.out.println("c, d");
        }
        // a:[0], b:[2]
        System.out.println(String.format("a:[%d], b:[%d]", c, d));
    }
}
