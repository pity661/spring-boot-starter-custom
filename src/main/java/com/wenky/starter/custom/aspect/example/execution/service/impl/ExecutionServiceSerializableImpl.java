package com.wenky.starter.custom.aspect.example.execution.service.impl;

import com.wenky.starter.custom.aspect.example.execution.service.ExecutionService;
import java.io.Serializable;
import org.springframework.stereotype.Service;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-07-07 14:31
 */
@Service
public class ExecutionServiceSerializableImpl implements ExecutionService, Serializable {
  private static final long serialVersionUID = 3747287848679150880L;

  @Override
  public void handle() {
    System.out.println("handle");
  }
}
