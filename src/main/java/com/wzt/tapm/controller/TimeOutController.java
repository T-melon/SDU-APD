package com.wzt.tapm.controller;

import com.wzt.tapm.service.TimeOutService;
import com.wzt.tapm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeOutController {
    @Autowired
    private TimeOutService timeOutService;

      @PostMapping("/getDemandDdlData")
      Result getList(){
        return timeOutService.getTimeDataList();
     }

}