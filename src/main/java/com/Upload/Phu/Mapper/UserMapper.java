package com.Upload.Phu.Mapper;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDTO userRequestDTO);

    void updateUser(@MappingTarget  User user, UserRequestDTO userRequestDTO);
}
