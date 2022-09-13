package com.lws.rawrblogend.service.impl;

import com.lws.rawrblogend.dto.BlogCreateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.entity.Blog;
import com.lws.rawrblogend.enums.BlogStatus;
import com.lws.rawrblogend.mapper.BlogMapper;
import com.lws.rawrblogend.respository.BlogRepository;
import com.lws.rawrblogend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    BlogMapper blogMapper;
    BlogRepository blogRepository;

    @Override
    public BlogDto create(BlogCreateRequest blogCreateRequest) {
        Blog blog = blogMapper.createEntity(blogCreateRequest);
        blog.setStatus(BlogStatus.DRAFT);
        Blog save = blogRepository.save(blog);
        return blogMapper.toDto(save);
    }

    @Autowired
    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
