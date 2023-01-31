package com.wenky.starter.custom.frame.cache;

import static org.junit.jupiter.api.Assertions.*;

import com.wenky.starter.custom.util.LoggerUtils;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class CacheGlueTest {
    @Autowired private CacheGlue cacheGlue;

    @Test
    public void cacheTest() throws InterruptedException {
        LoggerUtils.info(cacheGlue.mapCache());
        LoggerUtils.info(cacheGlue.redisCache());
        TimeUnit.SECONDS.sleep(1);
        LoggerUtils.info(cacheGlue.mapCache());
        LoggerUtils.info(cacheGlue.redisCache());
    }

    @Test
    public void ehCacheTest() throws InterruptedException {
        LoggerUtils.info(cacheGlue.ehCache());
        TimeUnit.SECONDS.sleep(1);
        LoggerUtils.info(cacheGlue.ehCache());
    }
}
