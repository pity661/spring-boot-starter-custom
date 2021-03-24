package com.wenky.starter.custom.frame.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedissonLockTest {
  @Autowired private RedissonLock redissonLock;

  @Test
  public void handleFunctionWithSeveralTryLockTest() {
    redissonLock.handleFunctionWithTryLock(a -> a + 1, 1, "aaa");
  }
}
