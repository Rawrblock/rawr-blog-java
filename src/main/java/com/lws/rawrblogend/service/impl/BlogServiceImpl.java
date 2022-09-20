package com.lws.rawrblogend.service.impl;

import com.lws.rawrblogend.dto.BlogCreateOrUpdateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.dto.BlogPageNumber;
import com.lws.rawrblogend.entity.Blog;
import com.lws.rawrblogend.enums.BlogStatus;
import com.lws.rawrblogend.exception.BizException;
import com.lws.rawrblogend.exception.ExceptionType;
import com.lws.rawrblogend.mapper.BlogMapper;
import com.lws.rawrblogend.respository.BlogRepository;
import com.lws.rawrblogend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    BlogMapper blogMapper;
    BlogRepository blogRepository;

    @Override
    public BlogDto create(BlogCreateOrUpdateRequest blogCreateOrUpdateRequest) {
        Blog blog = blogMapper.createEntity(blogCreateOrUpdateRequest);
        blog.setStatus(BlogStatus.DRAFT);
        Blog save = blogRepository.save(blog);
        return blogMapper.toDto(save);
    }

    @Override
    public List<BlogDto> getAllBlogs(BlogPageNumber blogPageNumber) {
        Slice<Blog> allBlogs = blogRepository.getAllBlogs(PageRequest.of(blogPageNumber.getCurrentPage(), blogPageNumber.getSize()));
        List<BlogDto> collect = allBlogs.getContent().stream().map(blogMapper::toDto).collect(Collectors.toList());
        return collect;
    }

    @Override
    public BlogDto getBlog(String id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (!blog.isPresent()) {
            throw new BizException(ExceptionType.BLOG_NOT_FOUND);
        }
        return blogMapper.toDto(blog.get());
    }

    @Override
    public BlogDto update(String id, BlogCreateOrUpdateRequest blogCreateOrUpdateRequest) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (!blog.isPresent()) {
            throw new BizException(ExceptionType.BLOG_NOT_FOUND);
        }
        blog.get().setCover(null);
        Blog saved = blogMapper.updateEntity(blog.get(), blogCreateOrUpdateRequest);
        return blogMapper.toDto(blogRepository.save(saved));
    }

    @Override
    public void delete(String id) {
        Blog blog = getBlogById(id);
        blog.setStatus(BlogStatus.CLOSED);
        blogRepository.save(blog);
    }

    private Blog getBlogById(String id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (!blog.isPresent()) {
            throw new BizException(ExceptionType.BLOG_NOT_FOUND);
        }
        return blog.get();
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
