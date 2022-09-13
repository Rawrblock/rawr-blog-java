package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
public class BlogDto {

    private String id;

    private String title;

    private String content;

    private FileDto cover;
}
