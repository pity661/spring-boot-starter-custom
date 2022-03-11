package com.wenky.starter.custom.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-09-04 10:45
 */
public class FifoQueueExample {
    public static void main(String[] args) throws InterruptedException {

        //    addExample();

        getExample();
    }

    private static void getExample() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        queue.add("init");
        // [1]查看队列首元素，不会删除 如果队列是空的返回null
        //    String peekResult = queue.peek();
        //      System.out.println(String.format("peekResult: %s, queueSize: %d", peekResult,
        // queue.size()));

        // [2]查看队列首元素，不会删除 如果队列是空的返回异常
        //      String elementResult = queue.element();
        //      System.out.println(String.format("elementResult: %s, queueSize: %d", elementResult,
        // queue.size()));

        // [3] 弹出队列首元素 如果是空的返回null
        //        String pollResult = queue.poll();
        //        System.out.println(String.format("pollResult: %s, queueSize: %d", pollResult,
        // queue.size()));

        // [4] 弹出队列首元素,在指定时间内不能成功获取 返回null
        //    String pollTimeoutResult = queue.poll(1, TimeUnit.SECONDS);
        //    System.out.println(
        //        String.format("pollTimeoutResult: %s, queueSize: %d", pollTimeoutResult,
        // queue.size()));

        // [5] 弹出队列首元素 如果是空的抛出异常
        //      String removeResult = queue.remove();
        //        System.out.println(String.format("removeResult: %s, queueSize: %d", removeResult,
        // queue.size()));

        // [6] 移除指定元素，成功返回true 失败返回false
        Boolean removeObjectResult = queue.remove("a");
        System.out.println(
                String.format(
                        "removeObjectResult: %s, queueSize: %d", removeObjectResult, queue.size()));

        // [7] 阻塞方法，直到take成功
        //    String takeResult = queue.take();
        //    System.out.println(String.format("takeResult: %s, queueSize: %d", takeResult,
        // queue.size()));
    }

    private static void addExample() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
        queue.add("init");
        // [1]添加时如果达到队列的上限会直接抛出 java.lang.IllegalArgumentException
        //        Boolean addResult = queue.add("1");
        //        System.out.println("addResult: " + addResult);

        // [2]达到上限 直接返回false，添加失败
        //        Boolean offerResult = queue.offer("1");
        //        System.out.println("offerResult: " + offerResult);

        // [3]达到上限,在指定时间内不能成功添加，返回失败
        offerTimeoutExample(queue);

        // [4]阻塞方法，直到put成功
        //        queue.put("1");
    }

    private static void offerTimeoutExample(BlockingQueue<String> queue)
            throws InterruptedException {
        new Thread(
                        () -> {
                            System.out.println("thread执行，开始sleep");
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("thread执行，结束sleep");
                            String pollResult = queue.poll();
                            System.out.println("pollResult: " + pollResult);
                        })
                .start();

        System.out.println("offer开始执行");
        Boolean offerResult = queue.offer("1", 2, TimeUnit.SECONDS);
        System.out.println("offerResult: " + offerResult);
    }
}
