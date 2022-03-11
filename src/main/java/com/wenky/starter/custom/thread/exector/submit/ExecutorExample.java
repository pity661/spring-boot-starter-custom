package com.wenky.starter.custom.thread.exector.submit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-06-29 11:06
 */
public class ExecutorExample {

    public static void main(String[] args) {
        executorBlockingTest();
    }

    private static void executorBlockingTest() {
        // 线程池 自定义线程名
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("custom - %d").build();
        // 任务队列
        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>(3);
        // 定义线程池
        // corePoolSize 核心线程数，当submit任务时未达到则+1
        // maximumPoolSize 核心线程数+非核心线程数 仅当任务队列满时才会创建,一起消费任务队列
        // keepAliveTime 当阻塞队列中没有任务时，设置为0，非核心线程立刻销毁
        // 当任务队列满时执行的策略
        // RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        // CallerRunsPolicy 通过main线程执行任务，直接调用Runnable::run方法
        // AbortPolicy 直接抛出异常
        // DiscardPolicy 不做任何处理，任务丢失了
        // DiscardOldestPolicy 丢弃下一个应该执行的任务,queue::poll

        ThreadPoolExecutor executorService =
                new ThreadPoolExecutor(
                        1,
                        2,
                        0,
                        TimeUnit.MILLISECONDS,
                        linkedBlockingQueue,
                        threadFactory,
                        (a, b) ->
                                System.out.println(
                                        String.format("workId被抛弃了:[%d]", getWork(a).getWorkId())));
        // 这里必须使用submit才能正常获取work信息
        IntStream.range(0, 9).forEach(i -> executorService.submit(new Work(i)));
        // 活跃线程数
        System.out.println("activeCount:" + executorService.getActiveCount());
        // 队列中的任务会继续执行，不能再往队列中添加任务
        executorService.shutdown();
        // 打印队列中的work信息
        linkedBlockingQueue
                .iterator()
                .forEachRemaining(
                        runnable ->
                                System.out.println(
                                        String.format(
                                                "队列中workId:[%d]", getWork(runnable).getWorkId())));
        System.out.println("size:" + linkedBlockingQueue.size());

        //    while(true) {
        //        System.out.println("activeCount:" + executorService.getActiveCount());
        //        System.out.println("size:" + linkedBlockingQueue.size());
        //    }

        // ？？
        //    LockSupport.park();
    }

    // 获取最原始提交的work对象
    private static Work getWork(Object futureTask) {
        try {
            // 从executorService::submit方法跟进去看依赖关系
            Field callableField = FutureTask.class.getDeclaredField("callable");
            callableField.setAccessible(true);
            Object callable = callableField.get(futureTask);

            Field taskField =
                    Stream.of(Executors.class.getDeclaredClasses())
                            .filter(
                                    single ->
                                            "java.util.concurrent.Executors$RunnableAdapter"
                                                    .equals(single.getName()))
                            .findFirst()
                            .get()
                            .getDeclaredField("task");
            taskField.setAccessible(true);
            return (Work) taskField.get(callable);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Work implements Runnable {
        private int workId;

        public Work(int workId) {
            this.workId = workId;
        }

        @Override
        public void run() {
            System.out.println(
                    String.format(
                            "threadName:[%s], workId:[%d]",
                            Thread.currentThread().getName(), workId));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public int getWorkId() {
            return workId;
        }
    }
}
