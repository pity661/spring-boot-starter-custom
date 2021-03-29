package com.wenky.starter.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 10:36
 */
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(simpleClientHttpRequestFactory());
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(120000); // ms
    factory.setConnectTimeout(120000); // ms
    factory.setBufferRequestBody(false);
    return factory;
  }
}
