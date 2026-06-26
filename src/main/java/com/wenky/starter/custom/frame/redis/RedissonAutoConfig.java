package com.wenky.starter.custom.frame.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-26 16:12
 */
// 直接运行是需要加该配置
@Configuration
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

        // 单机模式
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(
                        "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());

        // 加锁时没有指定过期时间（无参方法或传-1），会自动开启看门狗模式
        // 设置锁默认过期时间30s，同时通过scheduleExpirationRenewal自动续期
        // 这个参数可以调整默认过期时间
//        config.setLockWatchdogTimeout();


        return Redisson.create(config);
    }

    public static void main(String[] args) {
        System.out.println(normalizeTicksPerWheel(5));
        // 256
        System.out.println(1 << 8);
    }

    // 返回2的倍数
    private static int normalizeTicksPerWheel(int ticksPerWheel) {
        int normalizedTicksPerWheel;
        for(normalizedTicksPerWheel = 1; normalizedTicksPerWheel < ticksPerWheel; normalizedTicksPerWheel <<= 1) {
        }

        return normalizedTicksPerWheel;
    }
}
