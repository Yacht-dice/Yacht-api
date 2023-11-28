package com.example.yatchdice.authentication.application;

import com.example.yatchdice.authentication.domain.AuthTokens;
import com.example.yatchdice.authentication.infra.kakao.KakaoLoginParams;
import com.example.yatchdice.authentication.infra.naver.NaverLoginParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    // POST 요청 "/api/auth/kakao" 매핑
    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) { // Athorization Code를 파라미터로 받음
        // oAuthLoginService의 login 메서드를 호출, 인증 토큰을 받아서 반환
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

//    // POST 요청 "/api/auth/google" 매핑
//    @PostMapping("/google")
//    public ResponseEntity<AuthTokens> loginGoogle(@RequestBody GoogleLoginParams params) {
//        // oAuthLoginService의 login 메서드를 호출, 인증 토큰을 받아서 반환
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }

}
