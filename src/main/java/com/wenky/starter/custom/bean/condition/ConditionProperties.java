package com.wenky.starter.custom.bean.condition;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 21:50
 */
// the same effect to @ConditionalOnExpression
// @ConditionalOnProperty(prefix = "wenky.condition", value = "enable", havingValue = "true",
// matchIfMissing = false)
@ConditionalOnExpression(value = "'${wenky.condition.enable}'.equals('true')")
@ConfigurationProperties(prefix = "wenky.condition")
public class ConditionProperties {
  ConditionProperties() {
    LoggerUtils.construct();
  }

  private Boolean enable;

  public Boolean getEnable() {
    return enable;
  }

  public void setEnable(Boolean enable) {
    this.enable = enable;
  }
}
