package com.guisebastiao.api.controllers.dtos;

import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String email,
        String cpf,
        String phone
){
}
