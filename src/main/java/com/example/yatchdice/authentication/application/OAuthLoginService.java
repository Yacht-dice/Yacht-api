package com.example.yatchdice.authentication.application;

import com.example.yatchdice.authentication.domain.AuthTokens;
import com.example.yatchdice.authentication.domain.AuthTokensGenerator;
import com.example.yatchdice.authentication.domain.oauth.OAuthInfoResponse;
import com.example.yatchdice.authentication.domain.oauth.OAuthLoginParams;
import com.example.yatchdice.authentication.domain.oauth.RequestOAuthInfoService;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//  OAuth 인증을 통해 로그인하는 사용자에 대한 로직을 처리
@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    // OAuth 로그인을 수행하고, 인증 토큰을 반환
    public AuthTokens login(OAuthLoginParams params) {
        // OAuth 인증 정보를 요청
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        // 멤버를 찾거나 새로 생성한 후 해당 멤버의 ID를 반환
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        // 멤버 ID를 사용하여 인증 토큰을 생성해서 반환
        return authTokensGenerator.generate(memberId);
    }
    // 주어진 OAuth 정보를 사용하여 멤버를 찾거나 새로 생성
    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        // 이메일을 기반으로 멤버를 find
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }
    // 새 멤버를 생성하고 저장
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        // OAuth 정보를 사용하여 멤버 객체를 생성
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        // 멤버 객체를 저장하고 저장된 객체의 ID를 반환
        return memberRepository.save(member).getId();
    }
}