package com.wzt.tapm.service;

import com.wzt.tapm.mapper.TimeoutMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeOutService {
    @Autowired
    private  TimeoutMapper timeoutMapper;

    public Result getTimeDataList(){
        Result result;
        result = Result.getResult(ResultCodeEnum.SUCCESS,timeoutMapper.selectDemandTimeAndStatus());

        return result;

    }
}


