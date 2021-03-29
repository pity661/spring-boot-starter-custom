package com.wenky.starter.custom.config;

import com.wenky.starter.custom.frame.cache.CacheGlue;
import java.time.Duration;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-29 09:57
 */
@Import(CacheGlue.class)
public class CacheConfig {
  @Value("${spring.application.name}")
  private String applicationName;

  @Bean
  public KeyGenerator basicKeyGenerator() {
    return (target, method, params) ->
        applicationName
            + "_"
            + method.getName()
            + "_"
            + Stream.of(params)
                .map(String::valueOf)
                .collect(Collectors.joining("_", "CACHE_KEY_", ""));
  }

  @Bean
  @Primary
  public CacheManager mapCacheManager() {
    return new ConcurrentMapCacheManager();
  }

  @Bean
  @ConditionalOnProperty(value = "wenky.redisson.enable", havingValue = "true")
  @ConditionalOnMissingBean(name = "redisCacheManager")
  public CacheManager redisCacheManager(RedisConnectionFactory factory) {
    RedisCacheManager cacheManager =
        RedisCacheManager.builder(factory)
            .withCacheConfiguration("redisCache", getRedisCacheConfigurationWithTtl(1 * 60 * 60))
            .build();
    return cacheManager;
  }

  private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
    RedisCacheConfiguration redisCacheConfiguration =
        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds));
    return redisCacheConfiguration;
  }
}
