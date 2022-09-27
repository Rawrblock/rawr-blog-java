package com.lws.rawrblogend.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名应该在4个字符到64个字符之间")
    private String name;

    //    @NotBlank(message = "邮箱不能为空")
    private String email;

    //    @NotBlank(message = "密码不能为空")
//    @Size(min = 4, max = 64, message = "密码应该在4个字符到64个字符之间")
    private String password;

    private FileDto avatar;

}
