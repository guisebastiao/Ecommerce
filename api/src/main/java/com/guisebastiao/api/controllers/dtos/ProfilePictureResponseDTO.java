package com.guisebastiao.api.controllers.dtos;

import java.util.UUID;

public record ProfilePictureDTO(
        UUID id,
        String url
) {
}
