package com.example.yatchdice.exception.notfound;

import com.example.yatchdice.exception.CustomException;
import com.example.yatchdice.exception.ExceptionContext;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends CustomException {
    public NotFoundException(ExceptionContext exceptionContext) {
        super(HttpStatus.NOT_FOUND, exceptionContext.getCode(), exceptionContext.getMessage());
    }
}
