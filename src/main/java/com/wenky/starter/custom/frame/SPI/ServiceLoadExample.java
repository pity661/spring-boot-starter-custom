package com.wenky.starter.custom.frame.SPI;

import java.util.ServiceLoader;

/**
 * @program: spring-boot-starter-custom
 * @description: ServiceLoader#1
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-02-09 16:50
 */
public class ServiceLoadExample {
    public static void main(String[] args) {
        ServiceLoader<Animal> animals = ServiceLoader.load(Animal.class);
        animals.forEach(Animal::name);
    }
}
