package com.wenky.starter.custom.frame.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-29 10:08
 */
@Component
@CacheConfig(keyGenerator = "basicKeyGenerator")
public class CacheGlue {

  @Cacheable(cacheNames = "mapCache", cacheManager = "mapCacheManager")
  public Long mapCache() {
    return System.currentTimeMillis();
  }

  @Cacheable(cacheNames = "redisCache", cacheManager = "redisCacheManager")
  public Long redisCache() {
    return System.currentTimeMillis();
  }
}
