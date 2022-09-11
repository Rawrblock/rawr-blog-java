package com.lws.rawrblogend.controller;

import com.lws.rawrblogend.dto.TokenCreateRequest;
import com.lws.rawrblogend.dto.UserCreateRequest;
import com.lws.rawrblogend.dto.UserDto;
import com.lws.rawrblogend.mapper.UserMapper;
import com.lws.rawrblogend.service.UserService;
import com.lws.rawrblogend.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    UserService userService;

    UserMapper userMapper;

    // 用户创建
    @PostMapping
    public UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
        return userMapper.toVo(userService.create(userCreateRequest));
    }

    // 用户登录时创建token接口(登录接口)
    @PostMapping("/tokens")
    public String createToken(@RequestBody TokenCreateRequest tokenCreateRequest) {
        return userService.createToken(tokenCreateRequest);
    }

    @GetMapping("/me")
    public UserVo getCurrentUser() {
        UserDto userDto = userService.getCurrentUser();
        return userMapper.toVo(userDto);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
