package com.example.yatchdice.game.service;

import com.example.yatchdice.exception.notfound.NotFoundMemberException;
import com.example.yatchdice.game.dto.request.SocketRequest;
import com.example.yatchdice.game.dto.response.SocketResponse;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {
    private final MemberRepository memberRepository;
    public SocketResponse receiveMessage(SocketRequest socketRequest, long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        return SocketResponse.of(userId, member.getEmail(), socketRequest.getMessage());
    }
}
