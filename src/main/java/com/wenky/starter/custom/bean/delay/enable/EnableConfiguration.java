package com.wenky.starter.custom.bean.delay.enable;

import com.wenky.starter.custom.bean.delay.DelayConfiguration;
import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 21:26
 */
// [1]不能这样写，EnableProperties未注入所以EnableConfiguration永远不会注入
// @ConditionalOnBean(EnableProperties.class) // [1]
// @EnableConfigurationProperties(EnableProperties.class) // [1]

// [2]加了另外的配置后
@ConditionalOnBean(EnableProperties.class) // [2]
@AutoConfigureAfter(DelayConfiguration.class) // [2]
public class EnableConfiguration {
  EnableConfiguration() {
    LoggerUtils.construct();
  }
}
