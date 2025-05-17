package com.guisebastiao.api.controllers.mappers;

import com.guisebastiao.api.controllers.dtos.RegisterDTO;
import com.guisebastiao.api.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    RegisterDTO toDTO(User user);
    User toUser(RegisterDTO dto);
}
