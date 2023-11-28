package com.example.yatchdice.exception.badrequest;

import com.example.yatchdice.exception.ExceptionContext;

public class BadRequestTokenException extends BadRequestException{
    public BadRequestTokenException() {
        super(ExceptionContext.BAD_REQUEST_TOKEN);
    }
}
