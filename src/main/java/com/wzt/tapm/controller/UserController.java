package com.wzt.tapm.controller;

import com.wzt.tapm.entity.ResetUserBean;
import com.wzt.tapm.entity.UserBean;
import com.wzt.tapm.service.UserService;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/login")
    public Result login(UserBean userBean){
        return userService.login(userBean);
    }

    @PostMapping("/user/register")
    public Result register(UserBean userBean){
        return userService.register(userBean);
    }

    @PostMapping("/user/reset")
    public Result resetPw(ResetUserBean resetUserBean){
        return userService.resetPw(resetUserBean);
    }

    @UserLoginToken
    @PostMapping("/user/self")
    public Result getSelfInfo(){return userService.getSelfInfo();}

    @PostMapping("/user/technician")
    public Result getTechnicalName(){return userService.getTechnicalName();}

}
