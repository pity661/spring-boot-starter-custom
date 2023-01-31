package com.wenky.starter.custom.frame.redis.delay;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2022-09-26 15:12
 */
@Service
public class WorkerService {

    private final RedissonClient redissonClient;

    public WorkerService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void offer() {
        RBlockingQueue<Task> blockingQueue = redissonClient.getBlockingQueue("TASK");

        RDelayedQueue<Task> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);

        Task task = new Task();
        task.call();

        delayedQueue.offer(task, 10, TimeUnit.SECONDS);
    }

    //
    public void work() throws InterruptedException {
        RBlockingQueue<Task> blockingQueue = redissonClient.getBlockingQueue("TASK");

        // 开启客户端监听（必须调用）
        // 否者系统重启时拿不到已过期数据
        // 要等到系统第一次调用getDelayedQueue方法时才能开启监听
        redissonClient.getDelayedQueue(blockingQueue);

        while (true) {
            Task task = blockingQueue.take();
            // TODO 异步多线程执行，避免任务阻塞
            task.call();
        }
    }
}
