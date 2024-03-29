package com.wenky.starter.custom.frame.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
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

    @Cacheable(cacheNames = "eh-cache", cacheManager = "ehCacheManager")
    public Long ehCache() {
        return System.currentTimeMillis();
    }

    @Cacheable(cacheNames = "redisCache", cacheManager = "redisCacheManager")
    public Long redisCache() {
        return System.currentTimeMillis();
    }

    // CacheAspectSupport::findInCaches 552
    @Cacheable(cacheNames = "caffeineCache", cacheManager = "caffeineCacheManager")
    public Long caffeineCache() {
        // 如果缓存被gc清理，返回null，会再次执行业务逻辑
        //        new CaffeineCacheManager().getCache("caffeineCache")
        //                .get("key", () -> "value");
        return System.currentTimeMillis();
    }

    // 清除缓存的时候如果有多个cacheManager，需要指定。否则会按照默认（primary）的执行
    @CacheEvict(
            cacheNames = {"mapCache", "redisCache", "ehCache"},
            allEntries = true)
    public void cleanCache() {}

    @CacheEvict(
            cacheNames = {"redisCache"},
            cacheManager = "redisCacheManager",
            allEntries = true)
    public void cleanRedisCache() {}

    @CacheEvict(
            cacheNames = {"ehCache"},
            cacheManager = "ehCacheManager",
            allEntries = true)
    public void cleanEhCache() {}
}
