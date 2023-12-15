package com.example.yatchdice.game.dto.response;

import com.example.yatchdice.member.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class JoinResponse {
    private String nickname;
    private String image;

    public static JoinResponse of(Member member) {
        return JoinResponse.builder()
                .nickname(member.getNickname())
                .image(member.getImage())
                .build();
    }
}
