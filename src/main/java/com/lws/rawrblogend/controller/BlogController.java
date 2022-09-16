package com.lws.rawrblogend.controller;

import com.lws.rawrblogend.dto.BlogCreateOrUpdateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.dto.BlogPageNumber;
import com.lws.rawrblogend.mapper.BlogMapper;
import com.lws.rawrblogend.service.BlogService;
import com.lws.rawrblogend.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private BlogService blogService;

    private BlogMapper blogMapper;

    @PostMapping("/")
    public BlogVo create(@RequestBody BlogCreateOrUpdateRequest blogCreateOrUpdateRequest) {
        return blogMapper.toVo(blogService.create(blogCreateOrUpdateRequest));
    }

    @PostMapping("/{id}")
    public BlogVo update(@PathVariable String id, @RequestBody BlogCreateOrUpdateRequest blogCreateOrUpdateRequest) {
        return blogMapper.toVo(blogService.update(id, blogCreateOrUpdateRequest));
    }

    @GetMapping("/")
    public List<BlogVo> list(BlogPageNumber blogPageNumber) {
        List<BlogDto> blogs = blogService.getAllBlogs(blogPageNumber);
        return blogs.stream().map(blogMapper::toVo).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BlogVo getBlog(@PathVariable String id) {
        return blogMapper.toVo(blogService.getBlog(id));
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setBlogMapper(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }
}
