package com.lws.rawrblogend.dto;

import lombok.Data;

@Data
public class BlogPageNumber {

    // 当前页
    private Integer currentPage;

    // 每页条数
    private Integer size;

}
