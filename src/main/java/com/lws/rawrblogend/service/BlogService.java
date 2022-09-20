package com.lws.rawrblogend.service;

import com.lws.rawrblogend.dto.BlogCreateOrUpdateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.dto.BlogPageNumber;

import java.util.List;

public interface BlogService {

    BlogDto create(BlogCreateOrUpdateRequest blogCreateOrUpdateRequest);

    List<BlogDto> getAllBlogs(BlogPageNumber blogPageNumber);

    BlogDto update(String id, BlogCreateOrUpdateRequest blogCreateOrUpdateRequest);

    BlogDto getBlog(String id);

    void delete(String id);
}
