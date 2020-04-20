package com.sandbox.handle;

import com.sandbox.exception.BeanValidationException;
import com.sandbox.exception.FieldException;
import com.sandbox.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandle {

    @ExceptionHandler(value = BeanValidationException.class)
    public ResponseEntity<Object> handleBeanValidationException(BeanValidationException beanValidationException) {

        List<FieldException> fields = new ArrayList<FieldException>();
        for (ConstraintViolation violation : beanValidationException.getConstraintViolations()) {
            FieldException fieldException = FieldException.builder()
                    .field( getFieldWithDelimiter( violation.getPropertyPath().toString() ) )
                    .message(violation.getMessage())
                    .build();
            fields.add(fieldException);
        }
        ResponseException responseException = ResponseException.builder().message("Erro de validação").fields(fields).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseException);

    }

    private String getFieldWithDelimiter(String path) {
        if ( isStringValid(path) ){
            String[] parts = path.split("[.]");
            if(parts.length > 0)
                return parts[parts.length - 1];
        }
        return path;
    }

    private boolean isStringValid(String path) {
        return path != null && !path.isBlank() && !path.isEmpty();
    }

}
