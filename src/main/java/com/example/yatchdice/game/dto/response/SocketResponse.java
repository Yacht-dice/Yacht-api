package com.example.yatchdice.game.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SocketResponse {
    private String message;

    public static SocketResponse of(String message) {
        return SocketResponse.builder()
                .message(message)
                .build();
    }
}
