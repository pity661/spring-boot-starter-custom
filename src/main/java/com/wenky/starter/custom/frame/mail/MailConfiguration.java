package com.wenky.starter.custom.frame.mail;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-07 10:21
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(JavaMailSender.class)
@ConditionalOnProperty(value = "wenky.frame.switch.mail", havingValue = "true")
@AutoConfigureAfter(MailSenderAutoConfiguration.class)
public class MailConfiguration {

    // 注入配置参考 MailSenderPropertiesConfiguration
    @Bean
    @ConditionalOnMissingBean(name = "mailTemplate")
    public MailTemplate mailTemplate(JavaMailSender javaMailSender) {
        MailTemplate mailTemplate = new MailTemplate(javaMailSender);
        return mailTemplate;
    }
}
