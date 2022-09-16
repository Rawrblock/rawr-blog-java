package com.lws.rawrblogend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BlogDto {

    private String id;

    private String title;

    private String content;

    private FileDto cover;

    private Date createdTime;
}
