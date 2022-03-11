package com.wenky.starter.custom.thread.exector.submit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-29 17:08
 */
public class LockSupportExample {
    public static void main(String[] args) throws InterruptedException {

        lockSupportTest();
    }

    // 子线程阻塞
    // 主线程解锁
    // 主线程结束
    // 子线程结束
    private static void lockSupportTest() throws InterruptedException {
        // 主线程调用 unlock
        Thread thread =
                new Thread(
                        () -> {
                            System.out.println("子线程阻塞");
                            // [1]在子线程执行前unpark一次也有效
                            //            try {
                            //                TimeUnit.SECONDS.sleep(2);
                            //            } catch (InterruptedException e) {
                            //                e.printStackTrace();
                            //            }
                            // 无限期暂停当前线程
                            LockSupport.park();
                            System.out.println("子线程结束");
                        });
        thread.start();
        // [2]必须在子线程start之后执行才有效
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程解锁");
        LockSupport.unpark(thread);
        System.out.println("主线程结束");
    }
}
