package com.Oloola.Oloola.exceptions;

import me.alidg.errors.annotation.ExceptionMapping;
import me.alidg.errors.annotation.ExposeAsArg;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExceptionMapping(statusCode = NOT_FOUND, errorCode = "resource.not_found")

public class NotFoundException extends RuntimeException {
    @ExposeAsArg(0)
    private String paramName;

    @ExposeAsArg(1)
    private String paramValue;

    public NotFoundException(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }
}
