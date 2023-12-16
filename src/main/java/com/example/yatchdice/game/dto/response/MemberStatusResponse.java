package com.example.yatchdice.game.dto.response;

import com.example.yatchdice.game.util.MemberStatus;
import com.example.yatchdice.member.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberStatusResponse {
    private String nickname;
    private String image;
    private MemberStatus status;

    public static MemberStatusResponse of(Member member, MemberStatus status) {
        return MemberStatusResponse.builder()
                .nickname(member.getNickname())
                .image(member.getImage())
                .status(status)
                .build();
    }
}
