package com.wzt.tapm.controller;

import com.wzt.tapm.service.LogService;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.annotation.UserLoginToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * /log/all, /log/self, /log/project
 */
@RestController
public class LogController {
    @Resource
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @UserLoginToken
    @PostMapping("/log/all")
    public Result AllLog() {
        return logService.AllLog();
    }

    @UserLoginToken
    @PostMapping("/log/self")
    public Result SelfLog() {
        return logService.SelfLog();
    }

    @UserLoginToken
    @PostMapping("/log/demand")
    public Result ProjectLog(String demand_id) {
        return logService.DemandLog(demand_id);
    }
}
