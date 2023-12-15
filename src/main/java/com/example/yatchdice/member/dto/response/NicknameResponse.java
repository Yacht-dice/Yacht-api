package com.example.yatchdice.member.dto.response;

import com.example.yatchdice.member.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NicknameResponse {
    private Long userId;
    private String nickname;

    public static NicknameResponse of(Member member) {
        return NicknameResponse.builder()
                .userId(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}
