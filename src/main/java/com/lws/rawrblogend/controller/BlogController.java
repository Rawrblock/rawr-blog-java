package com.lws.rawrblogend.controller;

import com.lws.rawrblogend.dto.BlogCreateRequest;
import com.lws.rawrblogend.mapper.BlogMapper;
import com.lws.rawrblogend.service.BlogService;
import com.lws.rawrblogend.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private BlogService blogService;

    private BlogMapper blogMapper;

    @PostMapping("/")
    private BlogVo create(@RequestBody BlogCreateRequest blogCreateRequest) {
        return blogMapper.toVo(blogService.create(blogCreateRequest));
    }

    @GetMapping("/")
    private List<BlogVo> list() {
        return null;
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
