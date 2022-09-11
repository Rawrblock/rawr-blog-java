package com.lws.rawrblogend.service.impl;

import com.lws.rawrblogend.entity.User;
import com.lws.rawrblogend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseService {

    private UserService userService;

    protected User getCurrentUserEntity() {
        // 通过 Security对象上下文获取出用户信息对象
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserByUsername(authentication.getName());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
