package com.example.cper_core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "A password deve ter pelo menos: (6 caracteres, 1 maiúscula, 1 minúscula, 1 número e 1 símbolo especial).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}