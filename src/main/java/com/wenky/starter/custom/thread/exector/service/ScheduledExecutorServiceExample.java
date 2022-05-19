package com.wenky.starter.custom.thread.exector.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-17 14:07
 */
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        // 如果线程池中线程数量不够，等上一个任务执行完之后再执行下一个定时任务，造成任务延迟
        // ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
        // scheduleTest();
        // scheduleAtFixedRateTest();
        scheduleWithFixedDelayTest();
        // submitTest();
        System.out.println(Thread.currentThread().getName() + System.currentTimeMillis());
        TimeUnit.MINUTES.sleep(1);
    }

    public static void scheduleTest() {
        // 定时线程池的任务只会由核心线程执行
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 延时指定时间后执行，只执行一次
        scheduledExecutorService.schedule(
                () -> {
                    System.out.println(
                            Thread.currentThread().getName() + System.currentTimeMillis());
                },
                1,
                TimeUnit.SECONDS);
        // 执行shutdown后线程池停止，定时任务将不再执行
        // scheduledExecutorService.shutdown();
    }

    public static void scheduleAtFixedRateTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // 延迟指定时间执行，任务开始执行计时延迟 等上一个任务执行完执行
        scheduledExecutorService.scheduleAtFixedRate(
                () -> {
                    Long time = System.currentTimeMillis();
                    System.out.println("start: " + Thread.currentThread().getName() + time);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end: " + Thread.currentThread().getName() + time);
                    // 如果执行过程中发生异常就不会再继续执行了
                    // throw new NullPointerException();
                },
                1,
                2,
                TimeUnit.SECONDS);
        // 执行shutdown后线程池停止，定时任务将不再执行
        // scheduledExecutorService.shutdown();
    }

    public static void scheduleWithFixedDelayTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // 延迟指定时间执行，任务执行成功开始延迟计时
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> {
                    Long time = System.currentTimeMillis();
                    System.out.println("start: " + Thread.currentThread().getName() + time);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end: " + Thread.currentThread().getName() + time);
                    // 如果执行过程中发生异常就不会再继续执行了
                    // throw new NullPointerException();
                },
                1,
                2,
                TimeUnit.SECONDS);
        // 执行shutdown后线程池停止，定时任务将不再执行
        // scheduledExecutorService.shutdown();
    }

    public static void submitTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        // 提交任务后立刻执行
        scheduledExecutorService.submit(
                () ->
                        System.out.println(
                                Thread.currentThread().getName() + System.currentTimeMillis()));
    }
}
