package com.example.yatchdice.member.controller;

import com.example.yatchdice.authentication.domain.AuthTokensGenerator;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.dto.request.NicknameRequest;
import com.example.yatchdice.member.dto.response.NicknameResponse;
import com.example.yatchdice.member.repository.MemberRepository;
import com.example.yatchdice.member.service.MemberService;
import com.example.yatchdice.util.SecurityContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "members", description = "회원 관련 API")
@Slf4j
public class MemberController {
    private final AuthTokensGenerator authTokensGenerator;
    private final MemberService memberService;

    // 회원 정보 가져오는거
    @GetMapping("/test")
    public String test() {
        return SecurityContextUtil.getAuthenticatedUserEmail();
    }

    @Operation(summary = "닉네임 설정하기", description = """
            방 입장 전 화면에서 사용자는 닉네임 입력하고 join 또는 host 버튼을 눌러 방에 입장을 할 것입니다.
            
            사용자가 join 또는 host 버튼을 눌렀을 때 해당 방 정보에 subscribe을 할 텐데, 그 전에 이 api를 설정하여 먼저 닉네임을 설정해주세요.
            """)
    @PostMapping("/nickname")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseEntity<NicknameResponse> setNickname(@RequestBody NicknameRequest nicknameRequest){
        String email = SecurityContextUtil.getAuthenticatedUserEmail();
        return ResponseEntity.ok(memberService.setNickname(nicknameRequest, email));
    }
}