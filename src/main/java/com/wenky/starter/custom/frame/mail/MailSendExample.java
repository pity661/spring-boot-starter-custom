package com.wenky.starter.custom.frame.mail;

import com.wenky.starter.custom.util.LoggerUtils;
import com.wenky.starter.custom.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @program: spring-boot-starter-custom
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-07-06 10:16
 */
@Component
public class MailSendExample {

    @Autowired private MailProperties mailProperties;
    @Autowired private JavaMailSender javaMailSender;

    // 普通邮件
    public void sendSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        // 主题
        message.setSubject("TEST");
        // 发件人
        message.setFrom(mailProperties.getUsername());
        // 收件人 string ...
        message.setTo("huwenqi@olading.com");
        // 邮件正文
        message.setText(
                String.format("This is the test email template for your email:\n%s\n", "你好"));
        javaMailSender.send(message);
        LoggerUtils.info("TEST EMAIl SEND SUCCESS！");
    }

    // 带附件的邮件
    public void sendMimeMessage() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, Boolean.TRUE);
        // 主题
        helper.setSubject("你好");
        // html邮件正文
        helper.setText("<b style='color:red'>【你好，鸭蛋】</b>", Boolean.TRUE);
        // 添加附件
        helper.addAttachment("不理笨蛋.png", new File("/Users/huwenqi/Desktop/1.jpeg"));
        // 发件人
        helper.setFrom(mailProperties.getUsername());
        // 收件人 string ...
        helper.setTo("zhangdanya@algebraist.cn");
        javaMailSender.send(message);
    }

    // 接收邮件
    public void receiveMessage() throws MessagingException, ParseException {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");
        props.setProperty("mail.pop3.port", "110");
        //        props.setProperty("mail.pop3.host", "pop.qq.com");
        props.setProperty("mail.pop3.host", "pop.qiye.aliyun.com");
        Session session = Session.getInstance(props);
        try (Store store = session.getStore("pop3"); ) {
            store.connect(mailProperties.getUsername(), mailProperties.getPassword());
            try (Folder folder = store.getFolder("INBOX"); ) {
                folder.open(Folder.READ_ONLY);
                // SentDateTerm :: 发送时间条件
                SearchTerm sentDateTerm =
                        new AndTerm(
                                new SentDateTerm(
                                        ComparisonTerm.GE,
                                        DateUtils.parseDate("2022-07-01", "yyyy-MM-dd")),
                                new SentDateTerm(
                                        ComparisonTerm.LE,
                                        DateUtils.parseDate("2022-10-01", "yyyy-MM-dd")));

                // SubjectTerm :: 主题条件过滤 NotTerm list
                //                SearchTerm subjectTerm = new SubjectTerm("邮箱解析测试");
                // new OrTerm(new SubjectTerm("邮箱解析测试"), new SubjectTerm("wenky"));
                //                SearchTerm finalTerm = new AndTerm(subjectTerm, sentDateTerm);

                SearchTerm finalTerm = sentDateTerm;

                Message[] messages = folder.search(finalTerm);

                for (Message message : messages) {
                    MimeMessage mimeMessage = (MimeMessage) message;
                    // 中文标题解码
                    System.out.println(MimeUtility.decodeText(mimeMessage.getSubject()));
                    System.out.println(mimeMessage.getSender());
                    System.out.println(
                            DateFormatUtils.format(
                                    mimeMessage.getSentDate(), "yyyy-MM-dd HH:mm:ss"));
                    Optional.ofNullable(mimeMessage.getReceivedDate())
                            .map(date -> DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"))
                            .ifPresent(System.out::println);
                    System.out.println(mimeMessage.getMessageID());

                    saveAttachment(mimeMessage);

                    System.out.println(message.getSubject());
                }
                System.out.println(messages.length);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 下载附件
    private void saveAttachment(Part message) throws MessagingException, IOException {
        if (message.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) message.getContent();
            int count = multipart.getCount();

            for (int i = 0; i < count; i++) {
                // 复杂体邮件
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disposition = bodyPart.getDisposition();
                if (StringUtils.equalsAnyIgnoreCase(disposition, Part.ATTACHMENT, Part.INLINE)) {
                    InputStream inputStream = bodyPart.getInputStream();
                    FileUtils.copyToFile(
                            inputStream,
                            new File(
                                    "/Users/huwenqi/Desktop/"
                                            + "get1"
                                            + MimeUtility.decodeText(bodyPart.getFileName())));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart);
                } else {
                    if (bodyPart.getContentType().contains("name")
                            || bodyPart.getContentType().contains("application")) {
                        FileUtils.copyToFile(
                                bodyPart.getInputStream(),
                                new File(
                                        "/Users/huwenqi/Desktop/"
                                                + "get2"
                                                + MimeUtility.decodeText(bodyPart.getFileName())));
                    }
                }
            }
        }
    }
}
