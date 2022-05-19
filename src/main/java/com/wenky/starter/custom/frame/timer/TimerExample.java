package com.wenky.starter.custom.frame.timer;

import com.wenky.starter.custom.config.AsyncConfig;
import com.wenky.starter.custom.util.LoggerUtils;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-22 10:35
 */
// @Component
public class TimerExample {

    @Autowired private AsyncExample async;

    // fixedRate 任务开始执行计时，等上一个任务执行完，计时结束马上开始执行
    //    @Scheduled(fixedRate = 1000)
    public void fixedRate() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        LoggerUtils.methodInfo();
        System.out.println(System.currentTimeMillis());
    }

    // 1s
    // fixedDelay 任务执行成功后开始计时
    //    @Scheduled(fixedDelay = 1000)
    public void fixedDelay() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        LoggerUtils.methodInfo();
        System.out.println(System.currentTimeMillis());
    }

    //    @Scheduled(fixedDelay = 1000)
    public void asyncTest() {
        AsyncConfig.VALUE.set(1);
        LoggerUtils.info(AsyncConfig.VALUE.get());
        async.async();
    }

    //    @Scheduled(fixedDelay = 1000)
    public void asyncErrorTest() {
        async.execute(
                () -> {
                    throw new NullPointerException();
                });
    }

    @Scheduled(fixedDelay = 1000)
    public void test1() throws InterruptedException {
        LoggerUtils.methodInfo();
        TimeUnit.SECONDS.sleep(2);
    }

    @Scheduled(fixedDelay = 1000)
    public void test2() throws InterruptedException {
        LoggerUtils.methodInfo();
        TimeUnit.SECONDS.sleep(2);
    }

    @Scheduled(fixedDelay = 1000)
    public void test3() throws InterruptedException {
        LoggerUtils.methodInfo();
        TimeUnit.SECONDS.sleep(2);
    }

    @Scheduled(fixedDelay = 1000)
    public void test4() throws InterruptedException {
        LoggerUtils.methodInfo();
        TimeUnit.SECONDS.sleep(2);
        //        throw new NullPointerException("我是空指针");
    }

    @Scheduled(fixedDelay = 1000)
    public void test5() throws InterruptedException {
        LoggerUtils.methodInfo();
        TimeUnit.SECONDS.sleep(2);
    }
}
