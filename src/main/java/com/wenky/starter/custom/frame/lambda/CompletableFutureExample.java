package com.wenky.starter.custom.frame.lambda;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.util.StopWatch;

/**
 * @program: spring-boot-starter-custom
 * @description: 多线程执行任务编排
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-28 14:38
 */
public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //        single();
        //        System.out.println(getNames());
        //                System.out.println(fluent());
        //        System.out.println(fluentWithExecutorServiceThenCombine());
        //                System.out.println(fluentWithExecutorServiceThenCompose());

        applyAndCompose();

        stopWatch.stop();
        System.out.println("time: " + stopWatch.getTotalTimeMillis());
    }

    public static void single() throws InterruptedException, ExecutionException {
        Future<String> futureName = getNameAsync();

        doSomethingElse();

        String name = futureName.get();
        System.out.printf("name is %s", name);
        System.out.println();
    }

    private static void applyAndCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 =
                CompletableFuture.supplyAsync(() -> getName()).thenApply(name -> name.length());

        CompletableFuture<String> future2 =
                CompletableFuture.supplyAsync(() -> getName())
                        .thenCompose(name -> CompletableFuture.completedFuture(name + "_2"));

        CompletableFuture<String> future3 =
                CompletableFuture.supplyAsync(() -> getName())
                        .thenCombine(CompletableFuture.completedFuture("_3"), (a, b) -> a + b);

        System.out.println(future1.get());
        System.out.println(future2.get());
        System.out.println(future3.get());
    }

    // 不带线程池6秒
    private static List<String> fluent() {
        List<CompletableFuture<String>> nameFutures =
                IntStream.range(0, 4)
                        .boxed()
                        .map(i -> CompletableFuture.supplyAsync(() -> getName()))
                        .map(future -> future.thenApply(name -> name + getName()))
                        .map(
                                future ->
                                        future.thenCompose(
                                                name ->
                                                        CompletableFuture.supplyAsync(
                                                                () -> name + getName())))
                        .collect(Collectors.toList());
        return nameFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    // 带线程池 2s
    public static List<String> fluentWithExecutorServiceThenCombine() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<String>> nameFutures =
                IntStream.range(0, 4)
                        .boxed()
                        // 异步 1
                        .map(i -> CompletableFuture.supplyAsync(() -> getName(1), executorService))
                        // 同步 2
                        .map(future -> future.thenApply(name -> name + getName(2)))
                        // 不用等上面执行直接异步执行 1
                        .map(
                                future ->
                                        // 会在两个任务都执行完成后，把两个任务的结果合并
                                        // 两个任务并行执行
                                        future.thenCombine(
                                                CompletableFuture.supplyAsync(
                                                        () -> getName(3), executorService),
                                                (a, b) -> a + b))
                        .collect(Collectors.toList());
        List<String> result =
                nameFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        executorService.shutdown();
        return result;
    }

    // 3s
    public static List<String> fluentWithExecutorServiceThenCompose() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<String>> nameFutures =
                IntStream.range(0, 4)
                        .boxed()
                        // 异步 1
                        .map(i -> CompletableFuture.supplyAsync(() -> getName(1), executorService))
                        // 同步 2
                        .map(future -> future.thenApply(name -> name + getName(2)))
                        // 异步 3
                        .map(
                                future ->
                                        // 等前置任务执行完后，根据其结果执行后续任务
                                        // 前置任务需先执行完
                                        future.thenCompose(
                                                name ->
                                                        CompletableFuture.supplyAsync(
                                                                () -> name + getName(3),
                                                                executorService)))
                        .collect(Collectors.toList());
        List<String> result =
                nameFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        executorService.shutdown();
        return result;
    }

    // CompletableFuture.supplyAsync 可以指定线程池处理
    public static List<String> getNames() {
        List<CompletableFuture<String>> nameFutures =
                IntStream.range(0, 10)
                        .boxed()
                        .map(i -> CompletableFuture.supplyAsync(() -> getName()))
                        .collect(Collectors.toList());
        return nameFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static void doSomethingElse() throws InterruptedException {
        delay(2L);
        System.out.println("drink some water!");
    }

    public static Future<String> getNameAsync1() {
        return CompletableFuture.supplyAsync(() -> getName());
    }

    public static Future<String> getNameAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        new Thread(
                        () -> {
                            try {
                                String name = getName();
                                //               1.加上这段代码后futureName.get()将会被永久阻塞
                                //                int a = 1/ 0;
                                completableFuture.complete(name);
                            } catch (Exception e) {
                                e.printStackTrace();
                                // 2.直接把异常抛出去，futureName.get()的时候就不会被阻塞了
                                completableFuture.completeExceptionally(e);
                            }
                        })
                .start();
        return completableFuture;
    }

    public static String getName() {
        return getName(0);
    }

    public static String getName(Integer index) {
        try {
            delay(1L);
            return "wenky" + (index > 0 ? index : "");
        } catch (Exception e) {

        }
        return null;
    }

    public static void delay(Long timeout) throws InterruptedException {
        TimeUnit.SECONDS.sleep(timeout);
    }
}
