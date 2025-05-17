package com.guisebastiao.api.controllers.mappers;

import com.guisebastiao.api.controllers.dtos.LoginDTO;
import com.guisebastiao.api.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")

    LoginDTO toDTO(User user);

    User toUser(LoginDTO dto);
}
