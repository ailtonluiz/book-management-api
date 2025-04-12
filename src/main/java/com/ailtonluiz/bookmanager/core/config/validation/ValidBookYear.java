package com.ailtonluiz.bookmanager.core.config.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BookYearValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookYear {
    String message() default "El año del libro debe estar entre 1450 y el año actual.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}