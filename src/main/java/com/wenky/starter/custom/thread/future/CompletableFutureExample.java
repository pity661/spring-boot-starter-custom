package com.wenky.starter.custom.thread.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-25 12:44
 */
public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Integer result;
        // 1
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(CompletableFutureExample::calculate);
        TimeUnit.SECONDS.sleep(2);
        // 如果执行到这里的时候没拿到结果就将结果设置成指定值
        if (future.complete(1)) {
            System.out.println("改变结果了");
        }
        result = future.get(); // 阻塞获取异步处理结果
        System.out.println(result);
        result = future.getNow(10); // 直接获取结果，如果没执行完返回默认值
        System.out.println(result);

        // 2
        CompletableFuture<Integer> future1 = CompletableFuture.completedFuture(calculate());

        // 3
        CompletableFuture<Integer> thenFuture =
                future.whenComplete(
                        (t1, e) -> {
                            System.out.println(t1);
                        });
        future.join();
    }

    public static Integer calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 16 / 2;
    }
}
