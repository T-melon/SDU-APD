package com.wzt.tapm.controller;

import com.wzt.tapm.entity.UserBean;
import com.wzt.tapm.service.UserService;
import com.wzt.tapm.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * /login, /register
 */
@RestController
public class UserController {

    @Resource
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(UserBean userBean){
        return userService.login(userBean);
    }

    @PostMapping("/register")
    public Result register(UserBean userBean){
        return userService.register(userBean);
    }

}
