package com.wenky.starter.custom.aspect;

import com.wenky.starter.custom.aspect.controller.ControllerAspect;
import com.wenky.starter.custom.aspect.controller.verify.ControllerParamVerifyAspect;
import com.wenky.starter.custom.aspect.property.AspectProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 09:52
 */
@EnableConfigurationProperties(AspectProperties.class)
@Import({ControllerAspect.class, ControllerParamVerifyAspect.class})
public class AspectAutoConfiguration {}
