package com.lws.rawrblogend.config;

import com.lws.rawrblogend.exception.RestAuthenticationEntryPoint;
import com.lws.rawrblogend.filter.JwtAuthorizationFilter;
import com.lws.rawrblogend.service.UserService;
import com.lws.rawrblogend.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

// 自定义 Security配置类
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "RawrBlog";
    public static final long EXPIRATION_TIME = 60000;
    public static final String TOKEN_PREFIX = "Blog ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CREATE_TOKEN_URL = "/users/tokens";

    // 自定义 身份验证类
    UserService userService;

    RedisUtils redisUtils;

    // 自定义配置的身份验证方案（使用构造的方法注入）
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // CSRF禁用，因为不使用session
                .authorizeRequests() // 限定通过签名的请求
                .antMatchers(CREATE_TOKEN_URL).permitAll()
                .antMatchers("/users/").permitAll()
                .antMatchers("/files/**").permitAll()// 设置该请求可以直接访问
                .anyRequest().authenticated() // 表示除了上面定义的URL模式之外，用户访问其他URL都必须认证后访问(登录后访问)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService, redisUtils)) // 添加权限过滤器
                .exceptionHandling() // 允许配置错误处理
                .authenticationEntryPoint(restAuthenticationEntryPoint) // 开始身份验证过程
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //调整为让 Spring Security 不创建和使用 session
    }

    // 配置定义 处理身份认证的类  认证管理器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // 设置白名单
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/blogs/")
                .antMatchers(HttpMethod.GET, "/blogs/{id}");
    }

    // 注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }
}
