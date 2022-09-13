package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
public class BlogCreateRequest {

    private String id;

    private String title;

    private String content;

    private FileDto cover;

}
