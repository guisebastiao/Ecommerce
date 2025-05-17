package com.guisebastiao.api.controllers.dtos;

import com.guisebastiao.api.validation.ValidMaxFileSize;
import com.guisebastiao.api.validation.ValidMimetype;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UploadProfilePictureDTO(
        @NotNull(message = "Envie a sua foto de perfil")
        @ValidMimetype
        @ValidMaxFileSize(value = 5 * 1024 * 1024, message = "Sua foto de perfil tem que ser menor de 5MB")
        MultipartFile picture
) {

}
