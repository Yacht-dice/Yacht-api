package com.example.yatchdice.game.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PlayResponse {
    private Boolean play;

    public static PlayResponse of() {
        return PlayResponse.builder()
                .play(true)
                .build();
    }
}
