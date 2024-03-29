package com.wenky.starter.custom.aspect.example.common.bean;

import com.wenky.starter.custom.aspect.example.common.annotation.A2;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-02 11:40
 */
@A2
public class Man extends Human {
    @Override
    public void run() {
        System.out.println("Man runs.");
    }
}
