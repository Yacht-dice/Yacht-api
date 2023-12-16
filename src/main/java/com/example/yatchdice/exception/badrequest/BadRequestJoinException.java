package com.example.yatchdice.exception.badrequest;

import com.example.yatchdice.exception.ExceptionContext;

import static com.example.yatchdice.exception.ExceptionContext.BAD_REQUEST_JOIN;

public class BadRequestJoinException extends BadRequestException{
    public BadRequestJoinException() {
        super(BAD_REQUEST_JOIN);
    }
}
