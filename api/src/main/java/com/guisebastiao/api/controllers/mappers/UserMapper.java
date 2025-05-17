package com.guisebastiao.api.controllers.mappers;

import com.guisebastiao.api.controllers.dtos.UserDTO;
import com.guisebastiao.api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "phone", target = "phone")

    UserDTO toDTO(User user);
    User toUser(UserDTO dto);
}
