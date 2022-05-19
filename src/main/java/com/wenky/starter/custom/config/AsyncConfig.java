package com.wenky.starter.custom.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-22 10:27
 */
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    public static ThreadLocal<Integer> VALUE = new ThreadLocal<>();

    // default SimpleAsyncTaskExecutor
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        // setAllowCoreThreadTimeOut 和 setKeepAliveSeconds 同时设置才会结束 corePoolSize
        executor.setAllowCoreThreadTimeOut(Boolean.TRUE);
        executor.setKeepAliveSeconds(60);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        // 装饰者模式获取ThreadLocal属性
        executor.setTaskDecorator(getTaskDecorator());
        executor.setRejectedExecutionHandler(ScheduleConfig.getRejectedExecutionHandler());
        executor.initialize();
        return executor;
    }

    private TaskDecorator getTaskDecorator() {
        return runnable -> {
            // 调用异步执行线程
            Integer value = VALUE.get();
            return () -> {
                try {
                    // 当前执行任务线程
                    VALUE.set(value);
                    runnable.run();
                } finally {
                    VALUE.remove();
                }
            };
        };
    }
}
