package com.wenky.starter.custom.frame.restful;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-15 21:04
 */
public class PostParam {
  private Integer phone;
  private String name;

  public static PostParam newInstance() {
    PostParam param = new PostParam();
    param.setName("wenky");
    param.setPhone(10086);
    return param;
  }

  public Integer getPhone() {
    return phone;
  }

  public PostParam setPhone(Integer phone) {
    this.phone = phone;
    return this;
  }

  public String getName() {
    return name;
  }

  public PostParam setName(String name) {
    this.name = name;
    return this;
  }
}
