package com.example.yatchdice.authentication.infra.kakao;

import com.example.yatchdice.authentication.domain.oauth.OAuthApiClient;
import com.example.yatchdice.authentication.domain.oauth.OAuthInfoResponse;
import com.example.yatchdice.authentication.domain.oauth.OAuthLoginParams;
import com.example.yatchdice.authentication.domain.oauth.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KakaoApiClient implements OAuthApiClient {

    private static final String GRANT_TYPE = "authorization_code";

    @Value("${oauth.kakao.url.auth}") // application.yml 값들 주입
    private String authUrl;

    @Value("${oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${oauth.kakao.client-id}")
    private String clientId;

    private final RestTemplate restTemplate; // 외부 HTTP 서비스와 통신하기 위함

    @Override
    public OAuthProvider oAuthProvider() {  // OAuth Provider <- KAKAO
        return OAuthProvider.KAKAO;
    }


    @Override   // AccessToken 요청하는 메서드
    public String requestAccessToken(OAuthLoginParams params) {
        String url = authUrl + "/oauth/token";
        // 요청 헤더 설정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 요청 바디 설정
        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        // 요청 객체 생성
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        // 실제 외부  HTTP 요청 수행 및 응답 수신
        KakaoTokens response = restTemplate.postForObject(url, request, KakaoTokens.class);

        assert response != null;
        return response.getAccessToken();
    }

    @Override   // AccessToken 으로 사용자 정보 요청하는 메서드
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl + "/v2/user/me"; // 사용자 정보를 요청할 URL
        // HTTP 요청 헤더 설정
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        // HTTP 요청 바디 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]");
        // 요청 객체 생성
        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);
        // 실제 외부  HTTP 요청 수행 및 응답 수신
        return restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }
}