package com.example.yatchdice.game.controller;

import com.example.yatchdice.game.dto.request.SocketRequest;
import com.example.yatchdice.game.dto.response.JoinResponse;
import com.example.yatchdice.game.dto.response.MemberStatusResponse;
import com.example.yatchdice.game.dto.response.PlayResponse;
import com.example.yatchdice.game.dto.response.SocketResponse;
import com.example.yatchdice.game.service.GameService;
import com.example.yatchdice.util.SecurityContextUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private final GameService gameService;

    @MessageMapping("/games/{roomCode}")
    @SendTo("/sub/games/{roomCode}")
    public SocketResponse receiveMessage(@Payload SocketRequest socketRequest, SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode) {
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.receiveMessage(socketRequest, userId);
    }

    @MessageMapping("/games/{roomCode}/guest")
    @SendTo("/sub/games/{roomCode}/host")
    public MemberStatusResponse joinGame(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode) {
        log.info("joinGame()");
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.joinGame(userId);
    }

    @MessageMapping("/games/{roomCode}/host")
    @SendTo("/sub/games/{roomCode}/guest")
    public MemberStatusResponse playGame(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode) {
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.playGame(userId);
    }

    @SubscribeMapping("/games/{roomCode}/host")
    public MemberStatusResponse hostGame(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode){
        log.info("sdf");
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.hostGame(userId, roomCode);
    }

    @SubscribeMapping("/games/{roomCode}/guest")
    public MemberStatusResponse joinGameOnFirst(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode){
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.joinGameOnFirst(userId, roomCode);
    }
}
