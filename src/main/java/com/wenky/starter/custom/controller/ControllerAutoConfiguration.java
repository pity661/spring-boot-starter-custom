package com.wenky.starter.custom.controller;

import com.wenky.starter.custom.controller.health.HealthController;
import com.wenky.starter.custom.controller.health.HealthProperties;
import com.wenky.starter.custom.controller.support.GlobalDefaultExceptionHandler;
import com.wenky.starter.custom.controller.switch1.SwitchController;
import com.wenky.starter.custom.controller.switch1.SwitchProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-04 19:03
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@EnableConfigurationProperties({HealthProperties.class, SwitchProperties.class})
@AutoConfigureAfter(ErrorMvcAutoConfiguration.class)
public class ControllerAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "healthController", search = SearchStrategy.CURRENT)
  public HealthController healthController(HealthProperties healthProperties) {
    return new HealthController(healthProperties);
  }

  @Bean
  @ConditionalOnMissingBean(name = "globalDefaultExceptionHandler", search = SearchStrategy.CURRENT)
  public GlobalDefaultExceptionHandler globalDefaultExceptionHandler() {
    return new GlobalDefaultExceptionHandler();
  }

  @Bean
  @ConditionalOnMissingBean(name = "switchController", search = SearchStrategy.CURRENT)
  public SwitchController switchController(SwitchProperties switchProperties) {
    return new SwitchController(switchProperties);
  }
}
