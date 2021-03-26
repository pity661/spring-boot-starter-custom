package com.wenky.starter.custom.bean.delay.Limport;

import com.wenky.starter.custom.bean.delay.DelayConfiguration;
import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 21:46
 */
// [1]不能这样写，ImportProperties未注入所以ImportConfiguration永远不会注入
// @ConditionalOnBean(ImportProperties.class) // [1]
// @Import({ImportProperties.class, ImportConfig.class}) // [1]
// [2]加了另外的配置类DelayConfiguration后
@ConditionalOnBean(ImportProperties.class) // [2]
@AutoConfigureAfter(DelayConfiguration.class) // [2]
public class ImportConfiguration {
  ImportConfiguration() {
    LoggerUtils.construct();
  }
}
