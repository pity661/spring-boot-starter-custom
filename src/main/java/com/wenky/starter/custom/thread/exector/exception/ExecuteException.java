package com.wenky.starter.custom.thread.exector.exception;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @program: spring-boot-starter-custom
 * @description: 当线程池发生异常时，异常的线程就从池中删除了
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-09-01 11:05
 */
public class ExecuteException {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        exceptionTest();
    }

    private static void exceptionTest() throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 0,1,2,3
        IntStream.range(0, 3)
                .forEach(
                        i ->
                                executor.execute(
                                        () -> {
                                            for (; ; ) {
                                                System.out.println(
                                                        Thread.currentThread().getName());
                                                try {
                                                    TimeUnit.SECONDS.sleep(2);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }));
        Future future =
                executor.submit(
                        () -> {
                            for (; ; ) {
                                System.out.println(
                                        Thread.currentThread().getName() + "submit开始执行异常");
                                // 需要执行 future.get()获取异常信息
                                throw new NullPointerException();
                            }
                        });
        executor.execute(
                () -> {
                    for (; ; ) {
                        System.out.println(Thread.currentThread().getName() + "execute开始执行异常");
                        // 直接抛出异常信息
                        throw new NullPointerException();
                    }
                });
        // 通过submit提交的任务出现异常时需要通过future.get()获取异常信息
        System.out.println(future.get());
        executor.shutdown();
        countDownLatch.await();
    }
}
