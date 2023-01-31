package com.wenky.starter.custom.frame.redis.delay;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2022-09-22 15:25
 */
public interface DelayQueueLister<T> {

    void init();

    void invoke(T t);
}
