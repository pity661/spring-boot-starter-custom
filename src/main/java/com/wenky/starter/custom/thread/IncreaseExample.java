package com.wenky.starter.custom.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-08 00:52
 */
public class IncreaseExample {

    public static void main(String[] args) {
        atomicInteger();
        atomicStampedReference();
        longAdder();
    }

    // CAS实现
    public static void atomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        // 自旋 长时间循环导致CPU开销大
        // ABA 问题
        // 只能保证当个共享变量原子操作
        System.out.println(atomicInteger.incrementAndGet());
    }

    // 解决ABA问题 添加一个version版本概念
    public static void atomicStampedReference() {
        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1, 1);
        // CAS pair对象包含两个属性
        Boolean result = reference.compareAndSet(1, 5, 1, 2);
        System.out.println(result);
        Boolean result1 = reference.compareAndSet(5, 10, 1, 2);
        System.out.println(result1);
        System.out.println(reference.getReference());
    }

    // jdk8 升级
    public static void longAdder() {
        LongAdder longAdder = new LongAdder();
        // 对象内维系一个cell数组 根据线程分段执行CAS操作
        longAdder.increment();
        System.out.println(longAdder.intValue());
    }
}
