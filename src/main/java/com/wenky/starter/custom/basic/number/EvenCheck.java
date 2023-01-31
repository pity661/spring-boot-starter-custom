package com.wenky.starter.custom.basic.number;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-08-01 09:54
 */
public class EvenCheck {
    public static void main(String[] args) {
        // 偶数
        System.out.println(evenNumber(2));
        // 奇数
        System.out.println(oddNumber(7));
    }

    public static Boolean evenNumber(int num) {
        return (num & 1) == 0;
    }

    public static Boolean oddNumber(int num) {
        return (num & 1) == 1;
    }
}
