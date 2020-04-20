package com.sandbox.wrapper;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ResponseWrapper<T> {

    private final T data;
}
