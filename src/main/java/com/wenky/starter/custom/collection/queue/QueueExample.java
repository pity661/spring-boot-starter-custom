package com.wenky.starter.custom.collection.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-29 16:49
 */
public class QueueExample {
    public static void main(String[] args) {
        queueTest();
    }

    private static void queueTest() {
        // add offer
        //
        Queue<String> queue = new LinkedList<>();
        queue.add("add");
        queue.offer("offer");
        System.out.println(queue.size());
    }
}
