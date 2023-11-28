package com.example.yatchdice.exception.dto;

import com.example.yatchdice.exception.CustomException;
import com.example.yatchdice.exception.ExceptionContext;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private int code;
    private String message;

    public static ErrorResponse of(CustomException customException) {
        return ErrorResponse.builder()
                .code(customException.getCode())
                .message(customException.getMessage())
                .build();
    }
}
