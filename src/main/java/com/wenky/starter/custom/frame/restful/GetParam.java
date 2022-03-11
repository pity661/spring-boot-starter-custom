package com.wenky.starter.custom.frame.restful;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-15 22:35
 */
public class GetParam {

    private String phone;
    private String name;

    public static GetParam newInstance() {
        GetParam param = new GetParam();
        param.setName("wenky+");
        param.setPhone("10086");
        return param;
    }

    public String getPhone() {
        return phone;
    }

    public GetParam setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getName() {
        return name;
    }

    public GetParam setName(String name) {
        this.name = name;
        return this;
    }
}
