package com.wenky.starter.custom.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.wenky.starter.custom.frame.cache.CacheGlue;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-29 09:57
 */
@EnableCaching
@org.springframework.context.annotation.Configuration
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

    @Bean(name = {"ehCacheManager", "ehCacheManager1"})
    @ConditionalOnMissingBean(name = "ehCacheManager")
    public EhCacheCacheManager ehCacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager());
    }

    // https://www.ehcache.org/ehcache.xml
    public net.sf.ehcache.CacheManager ehCacheCacheManager() {
        CacheConfiguration cacheConfiguration =
                // name:缓存唯一标识 maxEntriesLocalHeap:对内存最大缓存对像数 0无限制
                new CacheConfiguration("eh-cache", 0);
        // 调度算法 default:LRU
        cacheConfiguration.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU);
        // elements是否永久有效，如果为true timeouts将被忽略，element将用不过期
        cacheConfiguration.eternal(Boolean.FALSE);
        // 失效前的空闲秒数
        cacheConfiguration.timeToIdleSeconds(120);
        // 失效前的存活秒数
        cacheConfiguration.timeToLiveSeconds(300);
        // 调用后不能再进行配置了
        // cacheConfiguration.freezeConfiguration();

        // default:120
        // cacheConfiguration.diskExpiryThreadIntervalSeconds(120);
        // default:30MB
        // cacheConfiguration.diskSpoolBufferSizeMB(30);
        // cacheConfiguration.timeoutMillis();

        CacheConfiguration cacheConfiguration2 = new CacheConfiguration("eh-cache2", 0);
        return net.sf.ehcache.CacheManager.create(
                new Configuration().cache(cacheConfiguration).cache(cacheConfiguration2));
    }

    @Bean
    @ConditionalOnProperty(value = "wenky.redisson.enable", havingValue = "true")
    @ConditionalOnMissingBean(name = "redisCacheManager")
    public CacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager =
                RedisCacheManager.builder(factory)
                        .withCacheConfiguration(
                                "redisCache", getRedisCacheConfigurationWithTtl(1 * 60 * 60))
                        .build();
        return cacheManager;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        RedisCacheConfiguration redisCacheConfiguration =
                RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration.entryTtl(Duration.ofSeconds(seconds));
        // serialize
        // default::RedisSerializer.string()
        redisCacheConfiguration.serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        RedisSerializer.string()));
        // default::RedisSerializer.java()
        redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer(
                                new ObjectMapper().registerModule(new JavaTimeModule()))));
        // NOT cache null value
        //        redisCacheConfiguration.disableCachingNullValues();
        return redisCacheConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(name = "caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        Caffeine caffeine =
                Caffeine.newBuilder()
                        // 内存溢出前GC会释放缓存内存，释放后SoftReference对象还在，get方法返回null
                        //                        .softValues()
                        // 每次gc都会释放缓存内存
                        .weakValues()
                        // 设置最后一次写入或访问后经过固定时间过期
                        .expireAfterWrite(30, TimeUnit.MINUTES)
                        // 初始的缓存空间大小
                        .initialCapacity(10)
                        // 缓存的最大条数
                        .maximumSize(50);

        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
