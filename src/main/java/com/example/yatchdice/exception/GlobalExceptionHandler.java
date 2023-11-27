package com.example.yatchdice.exception;

import com.example.yatchdice.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ErrorResponse.of(e));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonException(HttpMessageNotReadableException e) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(9997, "Json 형식이 올바르지 않습니다."));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleRequestMethodException(HttpRequestMethodNotSupportedException e) {

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(9998, "해당 요청에 대한 API가 존재하지 않습니다. 엔드 포인트를 확인해주시길 바랍니다. "));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unhandleException() {
        return ResponseEntity
                .internalServerError()
                .body(new ErrorResponse(9999, "예기치 않은 오류가 발생했습니다."));
    }
}
