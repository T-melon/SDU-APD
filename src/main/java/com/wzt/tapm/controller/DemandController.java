package com.wzt.tapm.controller;

import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.service.DemandService;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @GetMapping("/getToken")
    public void fetchToken(@RequestHeader("token") String Token){
        token = Token;
    }
}
