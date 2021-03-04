package com.wenky.starter.custom.aspect.controller.verify;

import java.lang.annotation.*;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 15:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Documented
public @interface NotVerify {}
