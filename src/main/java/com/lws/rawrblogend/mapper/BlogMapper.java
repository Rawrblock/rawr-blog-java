package com.lws.rawrblogend.mapper;

import com.lws.rawrblogend.dto.BlogCreateOrUpdateRequest;
import com.lws.rawrblogend.dto.BlogDto;
import com.lws.rawrblogend.entity.Blog;
import com.lws.rawrblogend.vo.BlogVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface BlogMapper {

    Blog createEntity(BlogCreateOrUpdateRequest blogCreateOrUpdateRequest);

    BlogDto toDto(Blog blog);

    BlogVo toVo(BlogDto blogDto);

    Blog updateEntity(@MappingTarget Blog blog, BlogCreateOrUpdateRequest blogCreateOrUpdateRequest);
}
