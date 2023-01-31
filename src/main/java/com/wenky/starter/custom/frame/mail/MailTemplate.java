package com.wenky.starter.custom.frame.mail;

import com.wenky.starter.custom.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-12-07 10:12
 */
public class MailTemplate {
    @Autowired private MailProperties mailProperties;
    private JavaMailSender javaMailSender;

    public MailTemplate(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        LoggerUtils.construct();
        LoggerUtils.info("JavaMailSender:" + this.javaMailSender);
    }

    // 发送带附件的邮件 https://blog.csdn.net/weixin_46822367/article/details/123893527
    // 使用模板发送邮件 FreeMarkerConfigurer
    public void sendEmailTemplate() {
        SimpleMailMessage message = initMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        javaMailSender.send(message);
        LoggerUtils.info("TEST EMAIl SEND SUCCESS！");
    }

    // 发送普通邮件
    private SimpleMailMessage initMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailProperties.getUsername());
        message.setTo("huwenqi@olading.com");
        //        message.setTo("wenky0413@gmail.com");
        message.setSubject("TEST");
        return message;
    }
}
