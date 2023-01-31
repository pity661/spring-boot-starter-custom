package com.wenky.starter.custom.util.reflect;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-06-20 11:31
 */
public class People {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public People setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getName() {
        return name;
    }

    public People setName(String name) {
        this.name = name;
        return this;
    }
}
