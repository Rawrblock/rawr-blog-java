package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
public class BlogCreateOrUpdateRequest {

    private String title;

    private String content;

    private FileDto cover;
}
