package com.guisebastiao.api.controllers.dtos;

import java.time.Instant;

public record AuthDTO(
    String token,
    Instant expires,
    UserDTO user
){
}
