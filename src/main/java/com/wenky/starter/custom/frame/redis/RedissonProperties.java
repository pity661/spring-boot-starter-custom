package com.wenky.starter.custom.frame.redis;

import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-19 14:09
 */
@Validated
@ConfigurationProperties(prefix = "wenky.redisson")
public class RedissonProperties {
    //  public RedissonProperties() {
    //    LoggerUtils.construct();
    //  }
    /** trylock wait time, overtime failed. default 1 s * */
    private Integer waitTime = 1;
    /** lock keep time, overtime auto release lock. default 5 min * */
    private Integer leaseTime = 300000;
    /** acquire sleep time. default 100 ms * */
    @Range(min = 100, max = 500, message = "sleep time must in [100, 500]")
    private Integer sleepTime = 100;
    /** enable use redis * */
    private Boolean enable;

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }

    public Integer getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(Integer leaseTime) {
        this.leaseTime = leaseTime;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
