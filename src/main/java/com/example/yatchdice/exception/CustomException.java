package com.example.yatchdice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
