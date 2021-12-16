package com.wzt.tapm.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wzt.tapm.entity.UserBean;
import org.springframework.stereotype.Service;

/**
 * Generate token
 */
@Service("TokenService")
public class TokenService {
    public String getToken(UserBean userBean) {
        String token;
        token= JWT.create().withAudience(userBean.getUsername())// 将 account 保存到 token 里面
                .sign(Algorithm.HMAC256(userBean.getPassword()));// 以 password 作为 token 的密钥
        return token;
    }
}
