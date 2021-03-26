package com.wenky.starter.custom.bean.Loverride;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 22:24
 */
@ConditionalOnMissingBean(OverrideConfiguration.class)
public class OverrideConfiguration {
  public OverrideConfiguration() {
    LoggerUtils.construct();
  }
}
