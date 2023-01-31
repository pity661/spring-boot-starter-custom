package com.wenky.starter.custom.frame.mail;

import java.text.ParseException;
import javax.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailSendExampleTest {

    @Autowired private MailSendExample mailSendExample;

    @Test
    public void test1() {
        mailSendExample.sendSimpleMailMessage();
    }

    @Test
    public void test2() throws MessagingException {
        mailSendExample.sendMimeMessage();
    }

    @Test
    public void test3() throws MessagingException, ParseException {
        mailSendExample.receiveMessage();
    }
}
