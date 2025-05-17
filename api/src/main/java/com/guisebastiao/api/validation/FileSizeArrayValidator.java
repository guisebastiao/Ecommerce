package com.guisebastiao.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileSizeArrayValidator implements ConstraintValidator<ValidMaxFileSize, MultipartFile[]> {
    private long maxSize;

    @Override
    public void initialize(ValidMaxFileSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(MultipartFile[] files, ConstraintValidatorContext context) {
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                if (file.getSize() > maxSize) {
                    return false;
                }
            }
        }

        return true;
    }
}
