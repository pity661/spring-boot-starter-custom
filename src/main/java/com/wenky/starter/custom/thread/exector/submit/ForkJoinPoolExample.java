package com.wenky.starter.custom.thread.exector.submit;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StopWatch;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-22 17:12
 */
public class ForkJoinPoolExample {

    public static void main(String[] args) throws InterruptedException {
        //        Executors.newCachedThreadPool();
        //        Executors.newFixedThreadPool(1);
        //        Executors.newSingleThreadExecutor();

        // ForkJoinPool
        ExecutorService executorService = Executors.newWorkStealingPool();
        ForkJoinPool forkJoinPool = (ForkJoinPool) executorService;
        //        executorService.submit();

        partitionTest();

        //        forkJoinTest();

        //        streamParallelTest();
    }

    public static void partitionTest() {
        // 0, 1
        List<Integer> material = IntStream.rangeClosed(0, 1).boxed().collect(Collectors.toList());
        System.out.println(material);
        System.out.println(material.size());
        // material size must large than 1
        List<List<Integer>> partition =
                Lists.partition(material, Math.max(material.size() / 2, (material.size() + 1) / 2));
        System.out.println(partition.get(0).size());
        System.out.println(partition.get(1).size());
    }

    public static void forkJoinTest() {

        //                private ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        List<Integer> material =
                IntStream.rangeClosed(0, 1000000).boxed().collect(Collectors.toList());
        Task task = new Task(material);

        // submit + join = invoke
        // [1] submit + join
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);
        Integer submitResult = forkJoinTask.join();
        stopWatch.stop();
        System.out.println(
                String.format(
                        "result: %s, consume time: %s",
                        submitResult, stopWatch.getLastTaskTimeNanos()));

        // [2] invoke
        stopWatch.start();
        Integer invokeResult = forkJoinPool.invoke(task);
        stopWatch.stop();
        System.out.println(
                String.format(
                        "result: %s, consume time: %s",
                        invokeResult, stopWatch.getLastTaskTimeNanos()));

        forkJoinPool.shutdown();
    }

    public static void streamParallelTest() {
        StopWatch stopWatch = new StopWatch();
        List<Integer> material =
                IntStream.rangeClosed(0, 1000000).boxed().collect(Collectors.toList());
        stopWatch.start();
        Integer parallelResult = material.stream().parallel().reduce(0, Integer::sum);
        stopWatch.stop();
        System.out.println(
                String.format(
                        "result: %s, consume time: %s",
                        parallelResult, stopWatch.getLastTaskTimeNanos()));

        stopWatch.start();
        Integer result = material.stream().reduce(0, Integer::sum);
        stopWatch.stop();
        System.out.println(
                String.format(
                        "result: %s, consume time: %s", result, stopWatch.getLastTaskTimeNanos()));
    }

    /** 有返回值 */
    public static class Task extends RecursiveTask<Integer> {

        private List<Integer> material;

        public Task(List<Integer> material) {
            this.material = material;
        }

        @Override
        protected Integer compute() {
            if (CollectionUtils.isEmpty(material) || material.size() < 10) {
                return material.stream().reduce(0, Integer::sum);
            }
            // make sure all material be compute
            List<List<Integer>> partition =
                    Lists.partition(
                            material, Math.max(material.size() / 2, (material.size() + 1) / 2));
            Task left = new Task(partition.get(0));
            Task right = new Task(partition.get(1));
            left.fork();
            right.fork();
            return left.join() + right.join();

            // work stealing
            // 工作队列 LIFO
            // 窃取任务 FIFO
        }
    }

    /** 无返回值 */
    public static class Action extends RecursiveAction {

        @Override
        protected void compute() {}
    }
}
