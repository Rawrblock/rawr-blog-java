package com.lws.rawrblogend.service;

import com.lws.rawrblogend.dto.BlogCreateRequest;
import com.lws.rawrblogend.dto.BlogDto;

public interface BlogService {

    BlogDto create(BlogCreateRequest blogCreateRequest);

}
