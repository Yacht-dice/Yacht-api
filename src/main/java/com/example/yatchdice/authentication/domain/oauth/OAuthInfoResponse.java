package com.example.yatchdice.authentication.domain.oauth;


// Access Toekn으로 요청한 외부 API 응답값을 서비스의 Model로 변환
public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}
