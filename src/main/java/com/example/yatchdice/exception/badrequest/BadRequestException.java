package com.example.yatchdice.exception.badrequest;

import com.example.yatchdice.exception.CustomException;
import com.example.yatchdice.exception.ExceptionContext;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {
    public BadRequestException(ExceptionContext exceptionContext) {
        super(HttpStatus.BAD_REQUEST, exceptionContext.getCode(), exceptionContext.getMessage());
    }
}
