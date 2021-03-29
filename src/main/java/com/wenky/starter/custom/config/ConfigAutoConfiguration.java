package com.wenky.starter.custom.config;

import com.wenky.starter.custom.frame.redis.RedissonConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 10:10
 */
@AutoConfigureAfter(RedissonConfiguration.class)
@Import({WebConfig.class, RestTemplateConfig.class, CacheConfig.class})
public class ConfigAutoConfiguration {}
