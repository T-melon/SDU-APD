package com.wzt.tapm.service;

import com.auth0.jwt.JWT;
import com.wzt.tapm.entity.DemandBean;
import com.wzt.tapm.mapper.DemandMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.wzt.tapm.controller.DemandController.token;

/**
 * Basic demand methods(create)
 */
@Service
public class DemandService {

    @Resource
    private final DemandMapper demandMapper;

    public DemandService(DemandMapper demandMapper) {
        this.demandMapper = demandMapper;
    }

    public Result createDemand(DemandBean demandBean){

        Result result;

        String title = demandBean.getTitle();
        String project = demandBean.getProject();
        String ddl = demandBean.getDdl();
        String doer = demandBean.getDoer();


        if(title == null||project == null||ddl == null||doer == null){
            result = Result.getResult(ResultCodeEnum.DEMAND_LACK);
        }else{

            demandBean.setCtime(new Date().toString());


            if(token != null){
                demandBean.setCer(JWT.decode(token).getAudience().get(0));

                int num = demandMapper.insertDemand(demandBean);

                if(num>0){
                    result = Result.getResult(ResultCodeEnum.SUCCESS);
                }else{
                    result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
                }

            }else{
                result = Result.getResult(ResultCodeEnum.FETCH_TOKEN_FAILED);
            }

        }
        return result;







    }
}
