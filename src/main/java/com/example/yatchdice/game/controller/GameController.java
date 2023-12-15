package com.example.yatchdice.game.controller;

import com.example.yatchdice.game.dto.request.SocketRequest;
import com.example.yatchdice.game.dto.response.JoinResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @MessageMapping("/games/{roomCode}/join")
    @SendTo("/sub/games/{roomCode}/host")
    public JoinResponse joinGame(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable String roomCode) {
        long userId = Long.parseLong((String) headerAccessor.getSessionAttributes().get("userId"));
        return gameService.joinGame(userId);
    }

    @MessageMapping("/games/{roomCode}/play")
    @SendTo("/sub/games/{roomCode}/guest")
    public PlayResponse playGame(@DestinationVariable String roomCode) {
        log.info(roomCode);
        return PlayResponse.of();
    }
}
