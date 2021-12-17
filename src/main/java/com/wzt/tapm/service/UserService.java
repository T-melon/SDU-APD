package com.wzt.tapm.service;

import com.auth0.jwt.JWT;
import com.wzt.tapm.entity.ResetUserBean;
import com.wzt.tapm.entity.UserBean;
import com.wzt.tapm.mapper.UserMapper;
import com.wzt.tapm.util.Result;
import com.wzt.tapm.util.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static com.wzt.tapm.interceptor.AuthenticationInterceptor.token;

/**
 * Basic user methods(register, login, resetPw, getSelfInfo, getTechnicalName)
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
        int identity = userBean.getIdentity();

        if(username == null||password == null){
            result = Result.getResult(ResultCodeEnum.LOGIN_LACK);
        }else{

            UserBean userBean1 = login(username);
            if(userBean1 == null){
                result = Result.getResult(ResultCodeEnum.LOGIN_ERROR);
            }else if(userBean1.getPassword().equals(password) && userBean1.getIdentity() == identity){
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

    public Result resetPw(ResetUserBean resetUserBean){

        Result result;

        String username = resetUserBean.getUsername();
        String old_password = resetUserBean.getOld_password();
        String new_password = resetUserBean.getNew_password();

        if(username == null||old_password == null||new_password == null){
            result = Result.getResult(ResultCodeEnum.REGISTER_LACK);
        }else if(old_password.length()>=20){
            result = Result.getResult(ResultCodeEnum.REGISTER_LENGTH_ERROR);
        }else{

            UserBean userBean1 = login(username);
            if(userBean1 == null){
                result = Result.getResult(ResultCodeEnum.LOGIN_ERROR);
            }else if(userBean1.getPassword().equals(old_password)){
                int num = userMapper.updatePasswordByUsername(new_password,username);
                if(num>0){
                    result = Result.getResult(ResultCodeEnum.SUCCESS);
                }else{
                    result = Result.getResult(ResultCodeEnum.UNKNOWN_REASON);
                }
            }else{
                result = Result.getResult(ResultCodeEnum.LOGIN_ERROR);
            }

        }

        return result;

    }

    public Result getSelfInfo(){

        Result result;
        String username = JWT.decode(token).getAudience().get(0);

        Map<Integer,Object> map = userMapper.selectUserInfoByUsername(username);

            result = Result.getResult(ResultCodeEnum.SUCCESS,map);

        return result;

    }

    public Result getTechnicalName(){

        Result result;
        List<String> list = userMapper.selectTechnicalName();

//        JSONArray jsonArray = JSONArray.fromObject(list);
//        result = Result.getResult(ResultCodeEnum.SUCCESS,jsonArray.toString());

        result = Result.getResult(ResultCodeEnum.SUCCESS,list);

//        result = Result.getResult(ResultCodeEnum.SUCCESS,JSON.toJSON(list).toString());
        return result;
    }

}
