package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;

    private String name;

    // 主要使用邮箱登录
    private String email;

    private String password;

    private FileDto avatar;

}
