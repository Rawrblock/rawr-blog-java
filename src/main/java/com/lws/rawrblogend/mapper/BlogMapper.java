package com.lws.rawrblogend.mapper;

import com.lws.rawrblogend.dto.BlogCreateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.entity.Blog;
import com.lws.rawrblogend.vo.BlogVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface BlogMapper {

    Blog createEntity(BlogCreateRequest blogCreateRequest);

    BlogDto toDto(Blog blog);

    BlogVo toVo(BlogDto blogDto);

}
