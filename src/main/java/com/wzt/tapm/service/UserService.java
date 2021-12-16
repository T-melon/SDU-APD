package com.wzt.tapm.service;

import com.wzt.tapm.entity.UserBean;
import com.wzt.tapm.mapper.UserMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Basic user methods(register, login)
 */
@Service
public class UserService {

    @Resource
    private final UserMapper userMapper;
    private final TokenService tokenService;

    public UserService(UserMapper userMapper, TokenService tokenService) {
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }


    public UserBean login(String username){
        return userMapper.selectUserByUsername(username);
    }


    public Result login(UserBean userBean){

        Result result;

        String username = userBean.getUsername();
        String password = userBean.getPassword();

        if(username == null||password == null){
            result = Result.getResult(ResultCodeEnum.LOGIN_LACK);
        }else{
            UserBean userBean1 = login(username);
            if(userBean1 == null){
                result = Result.getResult(ResultCodeEnum.LOGIN_ERROR);
            }else if(userBean1.getPassword().equals(password)){
                String token = tokenService.getToken(userBean1);
                result = Result.getResult(ResultCodeEnum.SUCCESS,token);
            }else{
                result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
            }

        }
        return result;
    }

    public Result register(UserBean userBean){

        Result result;

        String username = userBean.getUsername();
        String password = userBean.getPassword();
        String email = userBean.getEmail();

        if(username == null||password == null||email == null){
            result = Result.getResult(ResultCodeEnum.REGISTER_LACK);
        }else if(username.length()>=20||password.length()>=20){
            result = Result.getResult(ResultCodeEnum.REGISTER_LENGTH_ERROR);
        }else if(userMapper.selectUserByUsername(username) != null){
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
