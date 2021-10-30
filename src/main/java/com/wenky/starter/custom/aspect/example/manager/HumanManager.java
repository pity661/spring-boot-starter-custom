package com.wenky.starter.custom.aspect.example.manager;

import com.wenky.starter.custom.aspect.example.common.bean.Boy;
import com.wenky.starter.custom.aspect.example.common.bean.Human;
import com.wenky.starter.custom.aspect.example.common.bean.Man;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-02 11:41
 */
@Configuration
public class HumanManager {
  @Bean(name = "human")
  public Human getHuman() {
    return new Human();
  }

  @Bean(name = "man")
  public Man getMan() {
    return new Man();
  }

  @Bean(name = "boy")
  public Boy getBoy() {
    return new Boy();
  }
}
