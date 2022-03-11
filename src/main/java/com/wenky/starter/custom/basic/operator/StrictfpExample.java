package com.wenky.starter.custom.basic.operator;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-07 17:50
 */
public class StrictfpExample {
    // strictfp 可修饰在方法上也可修饰在类上，在类上所有方法生效
    public static strictfp void divideS() {
        float f = 0.333000000222f;
        double d = 0.0555500333333212d;
        double result = f / d;
        System.out.println(result);
    }

    public static void divide() {
        float f = 0.333000000222f;
        double d = 0.0555500333333212d;
        double result = f / d;
        System.out.println(result);
    }

    public static void main(String[] args) {
        divide();
        divideS();
    }
}
