package com.example.yatchdice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionContext {
    //인증 관련
    NOT_FOUND_MEMBER(1000, "존재하지 않는 멤버입니다."),
    BAD_REQUEST_TOKEN(1001, "토큰 값이 잘못된 요청입니다."),

    //게임방 관련
    BAD_REQUEST_HOST(2000, "해당 방의 host가 이미 존재합니다."),
    BAD_REQUEST_JOIN(2001, "해당 방의 guest가 이미 존재합니다."),
    NOT_FOUND_ROOM(2002, "해당 코드의 방이 존재하지 않습니다.")
    ;

    private final int code;
    private final String message;
}
