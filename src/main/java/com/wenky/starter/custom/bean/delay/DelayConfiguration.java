package com.wenky.starter.custom.bean.delay;

import com.wenky.starter.custom.bean.delay.Limport.ImportProperties;
import com.wenky.starter.custom.bean.delay.enable.EnableProperties;
import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.context.annotation.Import;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 22:40
 */
// [1]@EnableConfigurationProperties方式会无视目标类中的ConditionalOnExpression,目标类会不管配置条件自动注入
// @EnableConfigurationProperties({ImportProperties.class, EnableProperties.class}) // [1]

// [2]@Import方式，目标类中的ConditionalOnExpression生效
@Import({ImportProperties.class, EnableProperties.class}) // [2]
public class DelayConfiguration {
    DelayConfiguration() {
        LoggerUtils.construct();
    }
}
