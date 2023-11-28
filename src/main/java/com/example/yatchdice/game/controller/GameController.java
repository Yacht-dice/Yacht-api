package com.example.yatchdice.game.controller;

import com.example.yatchdice.game.dto.request.SocketRequest;
import com.example.yatchdice.game.dto.response.SocketResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "games", description = "인게임 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping()
    @SendTo("/gamers")
    public SocketResponse receiveMessage(@Payload SocketRequest socketRequest) {
        return SocketResponse.of(socketRequest.getMessage());
    }
}
