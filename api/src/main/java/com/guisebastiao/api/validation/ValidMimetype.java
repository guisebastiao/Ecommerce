package com.guisebastiao.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {MimetypeValidator.class, MimetypeArrayValidator.class})
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMimetype {
    String message() default "O arquivo deve ser uma imagem JPEG, JPG ou PNG";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
