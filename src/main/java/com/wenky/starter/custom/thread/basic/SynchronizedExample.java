package com.wenky.starter.custom.thread.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-08-06 16:13
 */
public class SynchronizedExample {
    public static void main(String[] args) throws InterruptedException {
        synchronizedTest();
    }

    private static void synchronizedTest() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        MyStack myStack = new MyStack();
        Thread thread1 =
                new Thread(
                        () -> {
                            try {
                                System.out.println(Thread.currentThread().getName() + "start");
                                System.out.println(myStack.pop());
                                System.out.println(Thread.currentThread().getName() + "end");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            countDownLatch.countDown();
                        });
        thread1.start();
        Thread thread2 =
                new Thread(
                        () -> {
                            try {
                                System.out.println(Thread.currentThread().getName() + "start");
                                System.out.println(myStack.pop());
                                System.out.println(Thread.currentThread().getName() + "end");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            countDownLatch.countDown();
                        });
        thread2.start();
        Thread.sleep(100L);
        Thread thread3 =
                new Thread(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "end");
                            myStack.push("a");
                            System.out.println(Thread.currentThread().getName() + "end");
                            countDownLatch.countDown();
                        });
        thread3.start();

        TimeUnit.SECONDS.sleep(4);
        Thread thread4 =
                new Thread(
                        () -> {
                            System.out.println(Thread.currentThread().getName() + "end");
                            myStack.push("b");
                            System.out.println(Thread.currentThread().getName() + "end");
                            countDownLatch.countDown();
                        });
        thread4.start();
        countDownLatch.await();
    }

    public static class MyStack {
        private List<String> list = new ArrayList<>();

        public synchronized void push(String value) {
            synchronized (this) {
                list.add(value);
                 notify();
                // [1] 如果这里用notifyAll，下面就不需要加
//                notifyAll();
            }
        }

        public synchronized String pop() throws InterruptedException {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + "size: " + list.size());
                // 这里需要重复判断list的size
                while (list.size() <= 0) {
                    // wait方法会释放锁 且仅当获取锁后才能调用
                    wait();
                }
                String result = list.remove(list.size() - 1);
                // [2] 如果push用notify，这里就要加notify
                 notify();
                return result;
            }
        }
    }
}
