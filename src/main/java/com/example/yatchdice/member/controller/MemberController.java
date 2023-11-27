package com.example.yatchdice.member.controller;

import com.example.yatchdice.authentication.domain.AuthTokensGenerator;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.repository.MemberRepository;
import com.example.yatchdice.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Slf4j
public class MemberController {
    private final AuthTokensGenerator authTokensGenerator;

    // 회원 정보 가져오는거
    @GetMapping("/test")
    public void test() {
        log.info("접속 이메일: {}", SecurityContextUtil.getAuthenticatedUserEmail());
    }
}