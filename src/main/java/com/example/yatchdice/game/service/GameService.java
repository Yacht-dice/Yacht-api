package com.example.yatchdice.game.service;

import com.example.yatchdice.exception.badrequest.BadRequestHostException;
import com.example.yatchdice.exception.badrequest.BadRequestJoinException;
import com.example.yatchdice.exception.notfound.NotFoundMemberException;
import com.example.yatchdice.exception.notfound.NotFoundRoomException;
import com.example.yatchdice.game.dto.request.SocketRequest;
import com.example.yatchdice.game.dto.response.JoinResponse;
import com.example.yatchdice.game.dto.response.MemberStatusResponse;
import com.example.yatchdice.game.dto.response.SocketResponse;
import com.example.yatchdice.game.util.MemberStatus;
import com.example.yatchdice.gameroom.domain.GameRoom;
import com.example.yatchdice.gameroom.repository.GameRoomRepository;
import com.example.yatchdice.member.domain.Member;
import com.example.yatchdice.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GameService {
    private final MemberRepository memberRepository;
    private final GameRoomRepository gameRoomRepository;

    public SocketResponse receiveMessage(SocketRequest socketRequest, long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        return SocketResponse.of(userId, member.getEmail(), socketRequest.getMessage());
    }

    public MemberStatusResponse joinGame(long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        return MemberStatusResponse.of(member, MemberStatus.GUEST);
    }

    public MemberStatusResponse playGame(long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        return MemberStatusResponse.of(member, MemberStatus.PLAYING);
    }

    @Transactional
    public MemberStatusResponse hostGame(long userId, String roomCode) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        GameRoom gameRoom = gameRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(NotFoundRoomException::new);
//        if (gameRoom.getHost() != null)
//            throw new BadRequestHostException();
        if (member.getMyGame() != null) {
            member.getMyGame().setHost(null);
        }
        if (member.getOtherGame() != null) {
            member.getOtherGame().setGuest(null);
        }
        gameRoom.setHost(member);
        return MemberStatusResponse.of(member, MemberStatus.HOST);
    }

    @Transactional
    public MemberStatusResponse joinGameOnFirst(long userId, String roomCode) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(NotFoundMemberException::new);
        GameRoom gameRoom = gameRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(NotFoundRoomException::new);
//        if (gameRoom.getGuest() != null)
//            throw new BadRequestJoinException();
        if (member.getMyGame() != null) {
            if (!member.getMyGame().equals(gameRoom))//테스트 위해 임시 추가
                member.getMyGame().setHost(null);
        }
        if (member.getOtherGame() != null) {
            member.getOtherGame().setGuest(null);
        }
        gameRoom.setGuest(member);
        return MemberStatusResponse.of(gameRoom.getHost(), MemberStatus.HOST);
    }
}
