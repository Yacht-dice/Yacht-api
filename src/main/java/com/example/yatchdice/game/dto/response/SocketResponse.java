package com.example.yatchdice.game.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SocketResponse {
    private Long userId;
    private String email;
    private String message;

    public static SocketResponse of(Long userId, String email, String message) {
        return SocketResponse.builder()
                .userId(userId)
                .email(email)
                .message(message)
                .build();
    }
}
