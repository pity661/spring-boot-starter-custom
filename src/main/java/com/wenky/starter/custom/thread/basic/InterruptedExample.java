package com.wenky.starter.custom.thread.basic;

import java.util.concurrent.CountDownLatch;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-06 14:29
 */
public class InterruptedExample {
    public static void main(String[] args) throws InterruptedException {
        interruptedTest();
    }
    // interrupted() 获取线程中断状态。如果为true，会清除当前中断位，
    private static void interruptedTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable =
                () -> {
                    System.out.println("start");
                    // false
                    System.out.println(Thread.currentThread().isInterrupted());
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.yield();
                    }
                    // 输出当前线程中断位信息，之后清除中断位
                    // true
                    System.out.println(Thread.interrupted());
                    // false
                    System.out.println(Thread.interrupted());
                    System.out.println("end");
                    countDownLatch.countDown();
                };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(100L);
        thread.interrupt();
        countDownLatch.await();
    }
}
