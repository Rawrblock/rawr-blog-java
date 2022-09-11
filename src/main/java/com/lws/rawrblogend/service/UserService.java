package com.lws.rawrblogend.service;

import com.lws.rawrblogend.dto.TokenCreateRequest;
import com.lws.rawrblogend.dto.UserCreateRequest;
import com.lws.rawrblogend.dto.UserDto;
import com.lws.rawrblogend.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    //----- 查 -----//
    // Security内置的身份验证类方法 （通过邮箱查看该用户是否存在）
    @Override
    User loadUserByUsername(String email);

    // 创建用户
    UserDto create(UserCreateRequest userCreateRequest);

    // 首次登录创建 构建token 进行登录
    String createToken(TokenCreateRequest tokenCreateRequest);

    // token校验成功 返回当前用户信息
    UserDto getCurrentUser();
}
