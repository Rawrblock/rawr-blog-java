package com.lws.rawrblogend.vo;

import lombok.Data;

@Data
public class UserVo extends BaseVo {

    private String name;

    private String email;

    private FileVo avatar;

    private Boolean locked;

    private Boolean enabled;

}
