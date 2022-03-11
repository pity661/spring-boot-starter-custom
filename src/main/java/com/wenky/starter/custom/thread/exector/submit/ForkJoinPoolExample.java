package com.wenky.starter.custom.thread.exector.submit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-12-22 17:12
 */
public class ForkJoinPoolExample {

    public static void main(String[] args) {
        // ForkJoinPool
        ExecutorService executorService = Executors.newWorkStealingPool();
    }
}
