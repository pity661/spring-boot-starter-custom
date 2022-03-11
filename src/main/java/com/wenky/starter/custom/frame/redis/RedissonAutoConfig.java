package com.wenky.starter.custom.frame.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-26 16:12
 */
@ConditionalOnMissingBean(name = "redissonClient")
public class RedissonAutoConfig {
    //  public RedissonAutoConfig() {
    //    LoggerUtils.construct();
    //  }
    @Autowired private RedisProperties redisProperties;

    /** can be customized * */
    @Bean(name = "redissonClient")
    RedissonClient redissonSingle() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(
                        "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());
        return Redisson.create(config);
    }
}
