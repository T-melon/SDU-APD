package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserBean selectUserByUsername(String username);
    int insertUser(UserBean userBean);
}