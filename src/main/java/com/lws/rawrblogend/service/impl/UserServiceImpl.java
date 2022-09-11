package com.lws.rawrblogend.service.impl;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lws.rawrblogend.config.SecurityConfig;
import com.lws.rawrblogend.dto.TokenCreateRequest;
import com.lws.rawrblogend.dto.UserCreateRequest;
import com.lws.rawrblogend.dto.UserDto;
import com.lws.rawrblogend.entity.User;
import com.lws.rawrblogend.exception.BizException;
import com.lws.rawrblogend.exception.ExceptionType;
import com.lws.rawrblogend.mapper.UserMapper;
import com.lws.rawrblogend.respository.UserRepository;
import com.lws.rawrblogend.service.UserService;
import com.lws.rawrblogend.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends BaseService implements UserService {


    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RedisUtils redisUtils;

    // Security= 通过用户名查询用户是否存在
    @Override
    public User loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        return user.get();
    }

    // 创建用户
    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkEmail(userCreateRequest.getEmail());
        User user = userMapper.createEntity(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return userMapper.toDto(save);
    }

    // 首次登录创建 构建token 进行登录
    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        // 通过 传入的邮箱判断是否存在用户
        User user = loadUserByUsername(tokenCreateRequest.getEmail());
        // 通过密码解密器 判断密码是否正确
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        String token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
        // 当登录成功 将 该用户信息 存入redis中
        redisUtils.setCacheObject(user.getEmail(), JSONUtil.toJsonStr(user), SecurityConfig.EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        return token;
    }

    @Override
    public UserDto getCurrentUser() {
        return userMapper.toDto(super.getCurrentUserEntity());
    }

    // ---- 封装公共方法
    // 判断邮箱是否存在
    public void checkEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_EMAIL_DUPLICATE);
        }
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }
}
