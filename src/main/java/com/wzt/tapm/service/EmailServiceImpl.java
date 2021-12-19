package com.wzt.tapm.service;

import com.wzt.tapm.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    public final JavaMailSender emailSender;

    @Resource
    private final UserMapper userMapper;

    public EmailServiceImpl(JavaMailSender emailSender, UserMapper userMapper) {
        this.emailSender = emailSender;
        this.userMapper = userMapper;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userMapper.selectEmailByUsername(to));
            message.setFrom("shujie_tian@163.com");
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            log.error("ExceptionUtil.stacktraceToString(exception)");
        }
    }
}
