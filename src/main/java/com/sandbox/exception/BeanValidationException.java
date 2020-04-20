package com.sandbox.exception;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class BeanValidationException extends RuntimeException {

    @Getter
    private final Set<ConstraintViolation<?>> constraintViolations;

    public BeanValidationException(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

}
