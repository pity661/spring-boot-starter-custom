package com.wenky.starter.custom.frame.timer;

import com.wenky.starter.custom.config.AsyncConfig;
import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-22 15:05
 */
@Component
public class AsyncExample {

    @Async
    public void async() {
        LoggerUtils.info(AsyncConfig.VALUE.get());
        System.out.println(Thread.currentThread().getName());
    }

    @Async
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
