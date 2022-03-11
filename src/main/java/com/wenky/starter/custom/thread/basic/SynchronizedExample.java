package com.wenky.starter.custom.thread.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
        CountDownLatch countDownLatch = new CountDownLatch(3);
        MyStack myStack = new MyStack();
        Thread thread1 =
                new Thread(
                        () -> {
                            try {
                                System.out.println("thread1 start");
                                myStack.pop();
                                System.out.println("thread1 end");
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
                                System.out.println("thread2 start");
                                myStack.pop();
                                System.out.println("thread2 end");
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
                            System.out.println("thread3 start");
                            myStack.push("a");
                            System.out.println("thread3 end");
                            countDownLatch.countDown();
                        });
        thread3.start();
        countDownLatch.await();
    }

    public static class MyStack {
        private List<String> list = new ArrayList<>();

        public synchronized void push(String value) {
            synchronized (this) {
                list.add(value);
                // notify();
                // [1] 如果这里用notifyAll，下面就不需要加
                notifyAll();
            }
        }

        public synchronized String pop() throws InterruptedException {
            synchronized (this) {
                if (list.size() <= 0) {
                    // wait方法会释放锁 且仅当获取锁后才能调用
                    wait();
                }
                String result = list.remove(list.size() - 1);
                // [2] 如果push用notify，这里就要加notify
                // notify();
                return result;
            }
        }
    }
}
