package com.wenky.starter.custom.thread.basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-16 19:24
 */
public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(
                        () -> {
                            synchronized (lockA) {
                                System.out.println("1 acquire a");
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                synchronized (lockB) {
                                    System.out.println("1 acquire b");
                                }
                            }
                            countDownLatch.countDown();
                        })
                .start();

        new Thread(
                        () -> {
                            synchronized (lockB) {
                                System.out.println("2 acquire b");
                                try {
                                    TimeUnit.MILLISECONDS.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                synchronized (lockA) {
                                    System.out.println("2 acquire a");
                                }
                            }
                            countDownLatch.countDown();
                        })
                .start();

        countDownLatch.await();
    }
}
