package com.wenky.starter.custom.frame.redis;

import com.wenky.starter.custom.util.LoggerUtils;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-19 11:58
 */
public class RedissonLock {
  @Autowired private StringRedisTemplate stringRedisTemplate;
  @Autowired private RedissonClient redissonClient;
  @Autowired private RedissonProperties redissonProperties;

  public <P, R> R handleFunctionWithLockImmediately(
      Function<P, R> function, P param, String lockKey) {
    return handleFunctionWithSeveralTryLock(function, param, lockKey, -1);
  }

  public <P, R> R handleFunctionWithLockImmediately(
      Function<P, R> function, P param, String lockKey, Integer leaseTime, TimeUnit timeUnit) {
    return handleFunctionWithSeveralTryLock(function, param, lockKey, -1, leaseTime, timeUnit);
  }

  public <P, R> R handleFunctionWithTryLock(Function<P, R> function, P param, String lockKey) {
    return handleFunctionWithSeveralTryLock(function, param, lockKey, null, null, null);
  }

  public <P, R> R handleFunctionWithTryLock(
      Function<P, R> function, P param, String lockKey, Integer leaseTime, TimeUnit timeUnit) {
    return handleFunctionWithSeveralTryLock(function, param, lockKey, null, leaseTime, timeUnit);
  }

  public <P, R> R handleFunctionWithSeveralTryLock(
      Function<P, R> function, P param, String lockKey, Integer waitTime) {
    return handleFunctionWithSeveralTryLock(function, param, lockKey, waitTime, null, null);
  }

  public <P, R> R handleFunctionWithSeveralTryLock(
      Function<P, R> function,
      P param,
      String lockKey,
      Integer waitTime,
      Integer leaseTime,
      TimeUnit timeUnit) {
    // set default value
    if ((waitTime == null || waitTime == 0)) {
      waitTime = redissonProperties.getWaitTime();
    }
    if (leaseTime == null || leaseTime == 0) {
      leaseTime = redissonProperties.getLeaseTime();
    }
    if (timeUnit == null) {
      timeUnit = TimeUnit.MILLISECONDS;
    }

    R result = null;
    RLock rLock = null;
    Boolean acquireLock = Boolean.FALSE;
    try {
      rLock = redissonClient.getLock(lockKey);
      if ((acquireLock = getLock(lockKey, rLock, waitTime, leaseTime, timeUnit))) {
        result = function.apply(param);
      }
    } catch (Exception e) {
      LoggerUtils.exception(e);
    } finally {
      // 如果执行超时释放锁失败
      if (acquireLock && rLock.isLocked()) {
        rLock.unlock();
      }
    }
    return result;
  }

  private Boolean getLock(
      String lockKey, RLock rLock, Integer waitTime, Integer leaseTime, TimeUnit timeUnit)
      throws InterruptedException {
    // without try if waitTime < 0
    if (waitTime <= 1) {
      return rLock.tryLock(waitTime, leaseTime, timeUnit);
    }
    // seconds to mill
    long timeoutMsecs = TimeUnit.SECONDS.toMillis(waitTime);
    while (timeoutMsecs > 0) {
      if (rLock.tryLock(1, leaseTime, timeUnit)) {
        return Boolean.TRUE;
      }
      timeoutMsecs = waitLockSleep(timeoutMsecs);
    }
    LoggerUtils.info(String.format("lockKey:[%s], acquire lock timeout.", lockKey));
    return Boolean.FALSE;
  }

  private long waitLockSleep(long timeoutMsecs) throws InterruptedException {
    timeoutMsecs -= redissonProperties.getSleepTime();
    Thread.sleep(redissonProperties.getSleepTime());
    return timeoutMsecs;
  }
}
