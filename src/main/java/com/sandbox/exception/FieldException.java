package com.sandbox.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FieldException {

    private String field;
    private String message;
}
