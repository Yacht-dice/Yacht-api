package com.example.yatchdice.gameroom.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CheckRoomResponse {
    private Boolean isExist;

    public static CheckRoomResponse of(Boolean isExist) {
        return CheckRoomResponse.builder()
                .isExist(isExist)
                .build();
    }
}
