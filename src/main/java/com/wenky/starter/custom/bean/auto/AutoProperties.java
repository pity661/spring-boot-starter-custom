package com.wenky.starter.custom.bean.auto;

import java.util.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 16:30
 */
@ConfigurationProperties(prefix = "wenky.auto")
public class AutoProperties {
    /** AutoProperties map description */
    private Map<String, String> map = new HashMap<>();
    /** AutoProperties string description */
    private String string;
    /** AutoProperties boolean description */
    private Boolean aBoolean;
    /** AutoProperties integer description */
    private Integer integer;
    /** AutoProperties long description */
    private Long aLong;
    /** AutoProperties enum description */
    private AutoEnum autoEnum;
    /** AutoProperties list description */
    private List<String> list = new ArrayList<>(Collections.singletonList("single"));

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getaLong() {
        return aLong;
    }

    public void setaLong(Long aLong) {
        this.aLong = aLong;
    }

    public AutoEnum getAutoEnum() {
        return autoEnum;
    }

    public void setAutoEnum(AutoEnum autoEnum) {
        this.autoEnum = autoEnum;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("map", map.toString())
                .append("string", string)
                .append("aBoolean", aBoolean)
                .append("integer", integer)
                .append("aLong", aLong)
                .append("autoEnum", autoEnum)
                .append("list", list.toString())
                .toString();
    }
}
