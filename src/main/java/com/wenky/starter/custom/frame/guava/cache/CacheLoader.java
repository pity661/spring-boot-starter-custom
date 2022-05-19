package com.wenky.starter.custom.frame.guava.cache;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-04-24 09:43
 */
public class CacheLoader extends com.google.common.cache.CacheLoader<String, String> {

    @Override
    public String load(String key) throws Exception {
        return null;
    }
}
