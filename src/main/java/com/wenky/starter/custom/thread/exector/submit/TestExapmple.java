package com.wenky.starter.custom.thread.exector.submit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-11-03 17:13
 */
public class TestExapmple {
    private static ThreadLocal<Integer> aa = new ThreadLocal<>();

    static {
        aa.set(1);
    }

    public static void main(String[] args) {
        int value1 = aa.get();
        System.out.println(value1);
        //        AtomicInteger i = new AtomicInteger();
        ExecutorService service = Executors.newFixedThreadPool(1);
        for (; ; ) {
            service.submit(
                    () -> {
                        //                int a ,b = i.incrementAndGet();
                        //                a = b;
                        //                System.out.println(a);

                        // ThreadLocal Value
                        Integer value = aa.get();
                        value = value == null ? 0 : value;
                        aa.set(++value);
                        System.out.println(value);
                    });
        }
    }
}
