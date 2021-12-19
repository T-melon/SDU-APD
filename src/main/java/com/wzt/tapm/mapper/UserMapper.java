package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.UserBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    UserBean selectUserByUsername(String username);
    int insertUser(UserBean userBean);
    int updatePasswordByUsername(String new_password, String username);
    Map<Integer,Object> selectUserInfoByUsername(String username);
    List<String> selectTechnicalName();
    String selectEmailByUsername(String username);
}