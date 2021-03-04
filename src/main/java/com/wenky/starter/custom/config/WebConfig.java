package com.wenky.starter.custom.config;

import com.wenky.starter.custom.config.resolver.UnderlineToCamelArgumentResolver;
import java.util.List;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-03 10:09
 */
public class WebConfig implements WebMvcConfigurer {
  /**
   * 添加参数解析，将参数的形式从下划线转化为驼峰
   *
   * @param argumentResolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new UnderlineToCamelArgumentResolver());
  }
}
