package com.example.yatchdice.member.service;

import com.example.yatchdice.exception.notfound.NotFoundMemberException;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.dto.request.NicknameRequest;
import com.example.yatchdice.member.dto.response.NicknameResponse;
import com.example.yatchdice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public NicknameResponse setNickname(NicknameRequest nicknameRequest, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(NotFoundMemberException::new);
        member.setNickname(nicknameRequest.getNickname());
        return NicknameResponse.of(member);
    }
}
