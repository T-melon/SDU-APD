package com.wzt.tapm.controller;

import com.wzt.tapm.service.EmailServiceImpl;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {

    @Resource
    private final EmailServiceImpl emailServiceImpl;

    public EmailController(EmailServiceImpl emailServiceImpl) {
        this.emailServiceImpl = emailServiceImpl;
    }
    @UserLoginToken
    @PostMapping("/demand/email")
    public Result sendEmail(String to, String subject, String text) {
        emailServiceImpl.sendSimpleMessage(to,subject,text);
        Result result;
        result = Result.getResult(ResultCodeEnum.SUCCESS);
        return result;
    }
}
