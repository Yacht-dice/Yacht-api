package com.example.yatchdice.authentication.application;

import com.example.yatchdice.authentication.domain.AuthTokens;
import com.example.yatchdice.authentication.infra.kakao.KakaoLoginParams;
import com.example.yatchdice.authentication.infra.naver.NaverLoginParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "로그인 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final OAuthLoginService oAuthLoginService;

    // POST 요청 "/api/auth/kakao" 매핑
    @Operation(summary = "카카오 인가코드 이용하여 토큰 발급받기", description = """
            카카오 인가코드를 request body에 담아 전달해주세요.
            
            response 값에 들어있는 acccessToken을 저장했다가 앞으로 서버에 api요청시 사용해주세요.
            
            후에 accessToken 사용시 request header에 Authorization값에 "Bearer 전달받은엑세스토큰" 형식으로 사용해주세요.
            """)
    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) { // Athorization Code를 파라미터로 받음
        // oAuthLoginService의 login 메서드를 호출, 인증 토큰을 받아서 반환
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @Operation(summary = "네이버 인가코드 이용하여 토큰 발급받기", description = """
            네이버 인가코드를 request body에 담아 전달해주세요.
            
            response 값에 들어있는 acccessToken을 저장했다가 앞으로 서버에 api요청시 사용해주세요.
            
            후에 accessToken 사용시 request header에 Authorization값에 "Bearer 전달받은엑세스토큰" 형식으로 사용해주세요.
            """)
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
