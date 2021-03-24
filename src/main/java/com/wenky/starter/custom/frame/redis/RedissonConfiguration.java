package com.wenky.starter.custom.frame.redis;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-19 14:08
 */
@EnableConfigurationProperties(RedissonProperties.class)
@Import({RedissonLock.class})
public class RedissonConfiguration {}
