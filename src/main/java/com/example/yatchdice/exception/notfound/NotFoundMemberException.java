package com.example.yatchdice.exception.notfound;

import com.example.yatchdice.exception.ExceptionContext;

public class NotFoundMemberException extends NotFoundException {

    public NotFoundMemberException() {
        super(ExceptionContext.NOT_FOUND_MEMBER);
    }
}
