package com.wzt.tapm.service;

import com.auth0.jwt.JWT;
import com.wzt.tapm.entity.LogBean;
import com.wzt.tapm.mapper.LogMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.wzt.tapm.interceptor.AuthenticationInterceptor.token;

/**
 * Basic log methods
 */
@Service
public class LogService {

    @Resource
    private final LogMapper logMapper;

    public LogService(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    public Result AllLog(){

        Result result;
        List<LogBean> list = logMapper.selectAllLog();
        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

        return result;

    }

    public Result SelfLog(){

        Result result;
        List<LogBean> list = logMapper.selectSelfLog(JWT.decode(token).getAudience().get(0));
        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

        return result;

    }

    public Result DemandLog(String demand_id){

        Result result;
        List<LogBean> list = logMapper.selectDemandLog(demand_id);
        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

        return result;

    }
}

