package com.wenky.starter.custom.aspect.example.execution.service.impl;

import com.wenky.starter.custom.aspect.example.execution.annotation.Target;
import com.wenky.starter.custom.aspect.example.execution.service.ExecutionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 13:58
 */
@Primary
@Service
public class ExecutionServiceImpl implements ExecutionService {
    @Override
    public void handle() {
        System.out.println("handle");
    }

    public void handlePlus() {
        System.out.println("handlePlus");
    }

    public void handleThrows() throws IndexOutOfBoundsException {
        System.out.println("handleThrows");
    }

    @Target
    public void handleAnnotation() {
        System.out.println("handleAnnotation");
    }
}
