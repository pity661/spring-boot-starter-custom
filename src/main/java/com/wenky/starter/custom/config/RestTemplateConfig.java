package com.wenky.starter.custom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 10:36
 */
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfig {
    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(simpleClientHttpRequestFactory());
    }

    @Bean
    public RestTemplate restTemplateWithoutEncode() {
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory());
        DefaultUriBuilderFactory uriTemplateHandler = new DefaultUriBuilderFactory();
        uriTemplateHandler.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        restTemplate.setUriTemplateHandler(uriTemplateHandler);
        return restTemplate;
    }

    // @Configuration(proxyBeanMethods = false) => 不代理bean方法，每个bean方法被调用多次返回的对象都是新创建的。否则从IOC容器中取
    // default是true，但是执行的时候如果没配置效果跟false一样（spring boot 2.2.0之后的版本默认不代理）
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(120000); // ms
        factory.setConnectTimeout(120000); // ms
        factory.setBufferRequestBody(false);
        return factory;
    }
}
