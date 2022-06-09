package com.wenky.starter.custom.frame.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-06-09 15:23
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Computer {
    private String name;

    private User user;

    public String getName() {
        return name;
    }

    public Computer setName(String name) {
        this.name = name;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Computer setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Computer{" + "name='" + name + '\'' + ", user=" + user + '}';
    }
}
