package com.wenky.starter.custom.aspect.example.execution.service.impl;

import com.wenky.starter.custom.aspect.example.execution.annotation.Target;
import com.wenky.starter.custom.aspect.example.execution.service.ExecutionService;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 14:47
 */
@Service(value = "executionServiceAnnotationImpl")
public class ExecutionServiceAnnotationImpl implements ExecutionService {
    @Target
    @Override
    public void handle() {
        System.out.println("handle");
    }
}
