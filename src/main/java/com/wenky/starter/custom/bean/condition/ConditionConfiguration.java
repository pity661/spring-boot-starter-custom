package com.wenky.starter.custom.bean.condition;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 21:47
 */
@ConditionalOnBean(ConditionProperties.class)
// 如果没有@ConditionalOnBean就算ConditionProperties不被注入ConditionConfiguration也会被注入
@AutoConfigureAfter(ConditionProperties.class)
public class ConditionConfiguration {
    ConditionConfiguration() {
        LoggerUtils.construct();
    }
}
