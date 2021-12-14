package com.wzt.tapm.mapper;

import com.wzt.tapm.entity.UserBean;
import org.springframework.stereotype.Repository;

/**
 * Basic user methods(register, login)
 */
@Repository
public interface UserMapper {
    UserBean selectUserByAccount(String account);
    int insertUser(UserBean userBean);
}