package com.wenky.starter.custom.aspect.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2023-02-01 16:16
 */
@ConfigurationProperties(prefix = "wenky.aspect.custom")
public class AspectCustomProperties {

    /** switch of custom aspect: enable/disable */
    private String controller;

    /** definition of custom aspect */
    private String pointcut;

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getPointcut() {
        return pointcut;
    }

    public void setPointcut(String pointcut) {
        this.pointcut = pointcut;
    }
}
