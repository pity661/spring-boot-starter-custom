package com.wenky.starter.custom.controller.switch1;

import static java.util.Locale.ENGLISH;

import com.wenky.starter.custom.controller.health.HealthProperties;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-01-15 11:16
 */
@ConfigurationProperties(prefix = "wenky.switch")
public class SwitchProperties {
  @Autowired private Environment env;
  @Autowired private HealthProperties healthProperties;

  @PostConstruct
  private void init() {
    runTask = Boolean.valueOf(env.getProperty("SYS_RUN_TASK"));
  }

  private boolean offline;
  private boolean runTask;
  private boolean logReportResultInfo;

  public Boolean getOffline() {
    return offline;
  }

  public void setOffline(Boolean offline) {
    this.offline = offline;
    if (this.offline) {
      healthProperties.setOnline(true);
    } else {
      healthProperties.setOnline(false);
    }
  }

  public Boolean getRunTask() {
    return runTask;
  }

  public void setRunTask(Boolean runTask) {
    this.runTask = runTask;
  }

  public Boolean getLogReportResultInfo() {
    return logReportResultInfo;
  }

  public void setLogReportResultInfo(Boolean logReportResultInfo) {
    this.logReportResultInfo = logReportResultInfo;
  }

  /**
   * field type should be boolean
   *
   * @param switchName
   * @return change result
   */
  public Boolean switchChange(String switchName) {
    if (StringUtils.isBlank(switchName)) {
      return null;
    }
    try {
      String readMethodName =
          "get" + switchName.substring(0, 1).toUpperCase(ENGLISH) + switchName.substring(1);
      Method readMethod = getClass().getMethod(readMethodName);
      String writeMethodName =
          "set" + switchName.substring(0, 1).toUpperCase(ENGLISH) + switchName.substring(1);
      Method writeMethod = getClass().getMethod(writeMethodName, Boolean.class);
      Object value = readMethod.invoke(this);
      // default open switch
      Boolean result = !Boolean.parseBoolean(String.valueOf(value));
      writeMethod.invoke(this, result);
      return result;
    } catch (Exception e) {
      return null;
    }
  }

  public List<String> getAllSwitchNames() {
    return Arrays.asList(BeanUtils.getPropertyDescriptors(getClass())).stream()
        .filter(single -> single.getReadMethod() != null && single.getWriteMethod() != null)
        .map(PropertyDescriptor::getName)
        .collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("offline", offline)
        .append("runTask", runTask)
        .append("logReportResultInfo", logReportResultInfo)
        .toString();
  }

  public boolean disableRunTask() {
    return !runTask;
  }
}
