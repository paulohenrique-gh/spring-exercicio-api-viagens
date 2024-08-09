package org.example.apiviagens.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validators.TripDateValidator;

import java.lang.annotation.*;

@Constraint(validatedBy = TripDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TripDateConstraint {
    String message() default "Start date must be before end date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

