package com.lws.rawrblogend.vo;

import lombok.Data;

@Data
public class BlogVo {

    private String id;

    private String title;

    private String content;

    private FileVo cover;

}
