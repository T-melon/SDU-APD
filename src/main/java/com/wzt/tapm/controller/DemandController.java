package com.wzt.tapm.controller;

import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.service.DemandService;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * /create
 */
@RestController
public class DemandController {

    @Resource
    private final DemandService demandService;

    public DemandController(DemandService demandService) {
        this.demandService = demandService;
    }

    @UserLoginToken
    @PostMapping("/create")
    public Result createDemand(DemandBean demandBean){
        return demandService.createDemand(demandBean);
    }

    public static String token;
    public String fetchToken(HttpServletRequest request) {
        token = request.getHeader("token");
        return token;
    }
}
