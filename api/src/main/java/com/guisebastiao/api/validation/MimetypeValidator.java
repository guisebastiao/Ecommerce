package com.guisebastiao.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class MimetypeValidator implements ConstraintValidator<ValidMimetype, MultipartFile> {
    private final List<String> allowedTypes = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file == null || allowedTypes.contains(file.getContentType());
    }
}
