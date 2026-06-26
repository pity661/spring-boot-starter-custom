package com.wenky.starter.custom.frame.redis.delay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @create: 2022-09-26 15:12
 */
public class Task implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    public void call() {
        logger.info("call invoke" + this);
    }
}
