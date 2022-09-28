package com.lws.rawrblogend.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVo extends BaseVo {

    private String name;

    private String email;

    private FileVo avatar;

    private List<RoleVo> roles;

    private Boolean locked;

    private Boolean enabled;

}
