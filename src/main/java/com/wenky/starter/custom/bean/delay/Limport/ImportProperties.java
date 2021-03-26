package com.wenky.starter.custom.bean.delay.Limport;

import com.wenky.starter.custom.util.LoggerUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 22:20
 */
@ConditionalOnExpression(value = "'${wenky.import.enable}'.equals('true')")
@ConfigurationProperties(prefix = "wenky.import")
public class ImportProperties {

  ImportProperties() {
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
