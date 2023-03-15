package com.wenky.starter.custom;

import com.wenky.starter.custom.frame.redis.delay.WorkerService;
import javax.annotation.PostConstruct;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-03-11 11:24
 */
// @Component
public class CustomApplicationRunner implements ApplicationRunner {

    private final WorkerService workerService;

    public CustomApplicationRunner(WorkerService workerService) {
        this.workerService = workerService;
    }

    // 提交任务
    @PostConstruct
    public void init() {
        workerService.offer();
    }

    // 执行分布式延迟任务
    @Override
    public void run(ApplicationArguments args) throws Exception {
        workerService.work();
    }
}
