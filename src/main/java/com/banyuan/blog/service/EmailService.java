package com.banyuan.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    //SimpleMailMessage message = new SimpleMailMessage();

    public void sendEmail(String TargetEmail, String title, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,false,"UTF-8");

        mimeMessageHelper.setFrom("banyuanblog@163.com");
        mimeMessageHelper.setTo(TargetEmail);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(content,true);
        javaMailSender.send(mimeMessage);
    }
}
