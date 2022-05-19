package com.wenky.starter.custom.thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-12 16:44
 */
public class InitExample {

    public static void runnableTest(Thread mainThread) {
        Runnable runnable =
                () -> {
                    System.out.println("Runnable start");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Runnable end");
                    LockSupport.unpark(mainThread);
                };
        new Thread(runnable).start();
    }

    public static void callableTest() throws ExecutionException, InterruptedException {
        Callable<Integer> callable =
                () -> {
                    TimeUnit.SECONDS.sleep(5);
                    return Integer.MAX_VALUE;
                };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println("futureTask: " + futureTask.isDone());
        System.out.println("futureTask: " + futureTask.get());
        System.out.println("futureTask: " + futureTask.isDone());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // [1]
        runnableTest(Thread.currentThread());
        LockSupport.park();
        System.out.println("main end");

        // [2]
        // callableTest();
    }
}
