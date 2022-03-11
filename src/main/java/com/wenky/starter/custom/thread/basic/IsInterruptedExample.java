package com.wenky.starter.custom.thread.basic;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-06 14:08
 */
public class IsInterruptedExample {
    public static void main(String[] args) throws InterruptedException {
        isInterruptedTest();
    }

    private static void isInterruptedTest() throws InterruptedException {
        Runnable runnable =
                () -> {
                    System.out.println("start");
                    // false
                    System.out.println(Thread.currentThread().isInterrupted());

                    // 执行循环直到线程被置于中断位 即 thread.interrupt()执行
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.yield();
                        // System.out.println(Thread.currentThread().getName());
                    }

                    // true
                    System.out.println(Thread.currentThread().isInterrupted());
                    // true
                    System.out.println(Thread.currentThread().isInterrupted());
                    // 先将线程置于中断位，然后执行sleep
                    // 线程抛出异常后，中断位被清除
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        // if any thread has interrupted the current thread.
                        // The interrupted status of the current thread is cleared when this
                        // exception is
                        // thrown.
                        e.printStackTrace();
                    }

                    // false
                    System.out.println(Thread.currentThread().isInterrupted());
                    System.out.println("end");
                };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
    }
}
