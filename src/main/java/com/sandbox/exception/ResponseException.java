package com.sandbox.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResponseException {
    private String message;
    private List<FieldException> fields;

}
