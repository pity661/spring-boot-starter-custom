package com.wenky.starter.custom.controller.health;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 19:35
 */
@ConfigurationProperties(prefix = "wenky.health")
public class HealthProperties {
    /** default health check path "/${context-path}/health" */
    private String path;
    /** health controller available */
    private Boolean online = true;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
