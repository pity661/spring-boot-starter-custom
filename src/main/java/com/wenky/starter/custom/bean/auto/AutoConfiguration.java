package com.wenky.starter.custom.bean.auto;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 16:29
 */
@EnableConfigurationProperties(AutoProperties.class)
@Import(AutoImportConfig.class)
public class AutoConfiguration {}
