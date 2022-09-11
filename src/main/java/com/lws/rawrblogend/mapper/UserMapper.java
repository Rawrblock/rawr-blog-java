package com.lws.rawrblogend.mapper;

import com.lws.rawrblogend.dto.UserCreateRequest;
import com.lws.rawrblogend.dto.UserDto;
import com.lws.rawrblogend.entity.User;
import com.lws.rawrblogend.vo.UserVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = FileMapper.class)
public interface UserMapper {

    User createEntity(UserCreateRequest userCreateRequest);

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);
}
