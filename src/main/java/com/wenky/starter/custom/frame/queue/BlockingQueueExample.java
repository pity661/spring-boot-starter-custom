package com.wenky.starter.custom.frame.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-22 11:20
 */
public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        //        test();
    }

    private static void test() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue(1);
        // 如果队列满了
        // 添加失败抛异常
        queue.add(1);
        // 添加失败返回false
        queue.offer(2);
        // 等待指定时间，添加失败返回false
        queue.offer(2, 1, TimeUnit.SECONDS);
        // 阻塞线程直到成功添加
        queue.put(3);

        // 拿走不删除，若为空返回null
        queue.peek();
        // 如果队列空了
        // 拿走并删除，若为空直接返回null
        queue.poll();
        // 等待指定时间
        queue.poll(1, TimeUnit.SECONDS);
        // 阻塞线程直到成功获取 await
        queue.take();

        // 仅遍历，不会删除
        queue.stream().peek(System.out::println);
        // 清空操作
        queue.clear();
    }
}
