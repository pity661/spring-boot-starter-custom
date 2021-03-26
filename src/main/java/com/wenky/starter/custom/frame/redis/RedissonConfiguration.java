package com.wenky.starter.custom.frame.redis;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-19 14:08
 */
// 在RedisAutoConfiguration注入后，且bean RedisProperties存在
@ConditionalOnBean(RedisProperties.class)
// 无视condition
@EnableConfigurationProperties(RedissonProperties.class)
// condition生效
@Import({RedissonAutoConfig.class, RedissonLock.class})
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedissonConfiguration {
  //  public RedissonConfiguration() {
  //    LoggerUtils.construct();
  //  }
}
