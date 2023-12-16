package com.example.yatchdice.exception.badrequest;

import com.example.yatchdice.exception.ExceptionContext;

import static com.example.yatchdice.exception.ExceptionContext.BAD_REQUEST_HOST;

public class BadRequestHostException extends BadRequestException{
    public BadRequestHostException() {
        super(BAD_REQUEST_HOST);
    }
}
