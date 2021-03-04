package com.wenky.starter.custom.aspect.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 13:26
 */
@ConfigurationProperties(prefix = "wenky.aspect")
public class AspectProperties {
  /** enable (controllerAspect switch) */
  private String controller;

  public String getController() {
    return controller;
  }

  public void setController(String controller) {
    this.controller = controller;
  }
}
