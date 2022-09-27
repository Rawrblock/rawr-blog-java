package com.lws.rawrblogend.filter;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lws.rawrblogend.config.SecurityConfig;
import com.lws.rawrblogend.entity.User;
import com.lws.rawrblogend.service.UserService;
import com.lws.rawrblogend.utils.RedisUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

// 自定义权限过滤器
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    UserService userService;
    RedisUtils redisUtils;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, RedisUtils redisUtils) {
        super(authenticationManager);
        this.userService = userService;
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. 获取出请求头中的token
        String header = request.getHeader(SecurityConfig.HEADER_STRING);
        // 2. 判断是否存在token && 是否以指定的前缀
        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            // 如果没有token 就直接跳转到下一个filter
            chain.doFilter(request, response);
            return;
        }
        // 解析Token
        String email;
        try {
            // 1. 校验Token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes())).build();
            String token = header.replace(SecurityConfig.TOKEN_PREFIX, "");
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            email = decodedJWT.getSubject();
            // 3. Token没过期，判断redis中是否存在数据
            String redisUser = redisUtils.getCacheObject(email);
            User user = JSONUtil.toBean(redisUser, User.class);
            // 当token没有过期,从redis中获取出用户数据返回
            if (Objects.isNull(user)) {
                // 没有数据时，就从mysql中
                User sqlUser = userService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, sqlUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                // 如果redis中存在数据
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            chain.doFilter(request, response);

        } catch (Exception e) {
            // 2. 当Token过期
            chain.doFilter(request, response);
            return;
        }


        // 通过用户名获取用户信息
        // 将解析出来的当前用户信息存入上下文，后期登录成功 token校验成功可以直接获取返回
    }

    // 验证JWT token 根据header构建新的Authenticatio
    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        if (header != null) {
            // 解构出token中的用户名信息
            String email = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
                    .build()
                    .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
                    .getSubject();
            if (email != null) {
                // 通过用户名获取用户信息
                User user = userService.loadUserByUsername(email);

                // 获取设置好的权限
                return new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
            }
        }
        return null;
    }

}
