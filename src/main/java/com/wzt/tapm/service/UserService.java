package com.wzt.tapm.service;

import com.wzt.tapm.entity.UserBean;
import com.wzt.tapm.mapper.UserMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service
public class UserService {

    @Resource
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Result login(UserBean userBean){

        Result result;

        String account = userBean.getAccount();
        String password = userBean.getPassword();

        if(account == null||password == null){
            result = Result.getResult(ResultCodeEnum.LOGIN_LACK);
        }else{
            UserBean userBean1 = userMapper.selectUserByAccount(account);

            if(userBean1 == null){
                result = Result.getResult(ResultCodeEnum.LOGIN_ERROR);
            }else if(userBean1.getPassword().equals(password)){
                result = Result.getResult(ResultCodeEnum.SUCCESS);
            }else{
                result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
            }

        }
        return result;
    }

    public Result register(UserBean userBean){

        Result result;

        String account = userBean.getAccount();
        String password = userBean.getPassword();
        String email = userBean.getEmail();

        if(account == null||password == null||email == null){
            result = Result.getResult(ResultCodeEnum.REGISTER_LACK);
        }else if(account.length()>=20||password.length()>=20){
            result = Result.getResult(ResultCodeEnum.REGISTER_LENGTH_ERROR);
        }else if(userMapper.selectUserByAccount(account) != null){
            result = Result.getResult(ResultCodeEnum.REGISTER_EXIST);
        }else{

            int num = userMapper.insertUser(userBean);

            if(num>0){
                result = Result.getResult(ResultCodeEnum.SUCCESS);
            }else{
                result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
            }

        }
        return result;
    }

}
