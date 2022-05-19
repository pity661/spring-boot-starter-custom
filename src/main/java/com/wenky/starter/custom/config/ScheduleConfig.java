package com.wenky.starter.custom.config;

import com.wenky.starter.custom.util.LoggerUtils;
import java.util.concurrent.RejectedExecutionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.util.ErrorHandler;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-22 10:23
 */
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    private static final Integer POOL_SIZE = 2;

    /**
     * 定时任务执行线程池配置
     *
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 定时线程池的任务只会由核心线程执行
        // [1]
        //        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(POOL_SIZE));

        // [2]
        taskRegistrar.setScheduler(getScheduler());
    }

    private TaskScheduler getScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(POOL_SIZE);
        // default new ThreadPoolExecutor.AbortPolicy()
        scheduler.setRejectedExecutionHandler(getRejectedExecutionHandler());
        // default TaskUtils.LoggingErrorHandler
        scheduler.setErrorHandler(getErrorHandler());
        scheduler.initialize();
        return scheduler;
    }

    private ErrorHandler getErrorHandler() {
        return LoggerUtils::exception;
    }

    public static RejectedExecutionHandler getRejectedExecutionHandler() {
        // 线程拒绝策略
        //        new ThreadPoolExecutor.AbortPolicy(); // 抛异常
        //        new ThreadPoolExecutor.CallerRunsPolicy(); // 在当前线程执行任务，阻塞式
        //        new ThreadPoolExecutor.DiscardOldestPolicy(); // 抛弃最先提交的任务
        //        new ThreadPoolExecutor.DiscardPolicy(); // 直接抛弃当前任务

        // 自定义线程拒绝策略
        RejectedExecutionHandler rejectedExecutionHandler =
                (task, executor) -> {
                    // 打印任务日志
                    LoggerUtils.info("任务被抛弃");
                };
        return rejectedExecutionHandler;
    }
}
