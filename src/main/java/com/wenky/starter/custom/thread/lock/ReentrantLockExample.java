package com.wenky.starter.custom.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-12 17:26
 */
public class ReentrantLockExample {

    // 默认非公平锁
    private static final Lock lock = new ReentrantLock();
    // 公平锁 按照获取锁的顺序排队
    // private static final Lock lock = new ReentrantLock(Boolean.TRUE);

    // condition的await会释放锁，但是signal不会释放锁
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();

    private static List<String> PRODUCTS = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // lockTest("A");
        // lockTest("B");

        producer("A");
        producer("B");
        producer("C");
        consumer("A");
        consumer("B");
    }

    public static void lockTest(String name) {
        new Thread(
                        () -> {
                            // 不会阻塞同步返回结果
                            Boolean tryLockResult = lock.tryLock();
                            System.out.println(tryLockResult);
                            // 会阻塞当前线程直到成功获取锁 // LockSupport.park()
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + ",获取锁");

                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            lock.unlock(); // LockSupport.unpark(head.nextNode.thread)
                            // 可重入锁，必须保证当前线程释放所有已获取
                            if (tryLockResult) {
                                lock.unlock();
                            }
                            System.out.println(Thread.currentThread().getName() + ",释放锁");
                        },
                        name)
                .start();
    }

    public static void producer(String name) {
        new Thread(
                        () -> {
                            try {
                                System.out.println(Thread.currentThread().getName() + " start");
                                lock.lock();
                                TimeUnit.SECONDS.sleep(1);
                                while (true) {
                                    PRODUCTS.add(name);
                                    System.out.println(Thread.currentThread().getName() + " add");
                                    System.out.println(
                                            PRODUCTS.stream().collect(Collectors.joining(",")));
                                    conditionB.signal();
                                    // 会释放当前线程获取到的锁进入到条件队列中
                                    conditionA.await();
                                }
                            } catch (Exception e) {

                            } finally {
                                lock.unlock();
                            }
                        },
                        "producer" + name)
                .start();
    }

    public static void consumer(String name) {
        new Thread(
                        () -> {
                            try {
                                System.out.println(Thread.currentThread().getName() + " start");
                                lock.lock();
                                while (true) {
                                    if (PRODUCTS.size() < 3) {
                                        // 会释放当前线程获取到的锁进入到条件队列中
                                        conditionB.await();
                                    } else {
                                        System.out.println(
                                                Thread.currentThread().getName()
                                                        + " remove "
                                                        + PRODUCTS.remove(PRODUCTS.size() - 1));
                                        // 只唤醒一个任意等待线程
                                        // 用这个方法list中最大数为3，仅一个producer能拿到锁
                                        // 不会释放锁
                                        conditionA.signal();
                                        // 唤醒所有等待线程
                                        // 用这个方法list中最大数为5，所有producer都能拿到锁
                                        // conditionA.signalAll();
                                    }
                                }
                            } catch (Exception e) {

                            } finally {
                                lock.unlock();
                            }
                        },
                        "consumer" + name)
                .start();
    }
}
