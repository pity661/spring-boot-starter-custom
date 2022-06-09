package com.wenky.starter.custom.thread.exector.submit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-06-08 13:35
 */
public class ParallelExample {

    public static void main(String[] args) {
        // ForkJoinPool 默认线程数
        //        Integer defaultProcessorSize = getAvailableProcessors();
        //        System.out.println(defaultProcessorSize);

        // 默认执行的线程池
        // 初始化逻辑 ForkJoinPool::makeCommonPool
        //        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "10");
        //        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        // 默认线程数
        //        ExecutorService defaultExecutorService = Executors.newWorkStealingPool();
        // 自定义线程数
        //        ExecutorService executorService = Executors.newWorkStealingPool(10);

        runExample(Thread.currentThread());
        System.out.println("finish");
    }

    public static Integer getAvailableProcessors() {
        Integer defaultProcessorSize = Runtime.getRuntime().availableProcessors();
        return defaultProcessorSize;
    }

    public static void runExample(Thread mainThread) {
        ExecutorService executorService = Executors.newWorkStealingPool(10);
        executorService.submit(
                () -> {
                    IntStream.range(1, 100)
                            .parallel()
                            .forEach(
                                    i ->
                                            System.out.println(
                                                    Thread.currentThread().getName() + ":" + i));
                    LockSupport.unpark(mainThread);
                });
        LockSupport.park();
        executorService.shutdown();
    }
}
