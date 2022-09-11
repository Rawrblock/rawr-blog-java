package com.lws.rawrblogend.mapper;

import com.lws.rawrblogend.dto.FileDto;
import com.lws.rawrblogend.dto.FileUploadRequest;
import com.lws.rawrblogend.entity.File;
import com.lws.rawrblogend.vo.FileVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File createEntity(FileUploadRequest fileUploadRequest);

    FileVo toVo(FileDto fileDto);

    FileDto toDto(File file);
}
