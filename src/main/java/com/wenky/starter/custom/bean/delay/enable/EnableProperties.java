package com.wenky.starter.custom.bean.delay.enable;

import com.wenky.starter.custom.util.LoggerUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 22:18
 */
// 需要在spring.factories中定义，或通过@EnableConfigurationProperties注入
@ConditionalOnProperty(
    prefix = "wenky.enable",
    value = "enable",
    havingValue = "true",
    matchIfMissing = false)
@ConfigurationProperties(prefix = "wenky.enable")
public class EnableProperties {
  EnableProperties() {
    LoggerUtils.construct();
  }

  private Boolean enable;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("enable", enable).toString();
  }
}
