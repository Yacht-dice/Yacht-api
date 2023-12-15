package com.example.yatchdice.gameroom.controller;

import com.example.yatchdice.gameroom.domain.GameRoom;
import com.example.yatchdice.gameroom.repository.GameRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
@Slf4j
public class GameRoomController {
    private final GameRoomRepository gameRoomRepository;

    // 방 코드 생성 후 주는거
    @GetMapping("/code")
    public String generateUniqueRoomCode() {
        String roomCode;
        do {
            roomCode = generateRandomRoomCode();
        } while (gameRoomRepository.existsByRoomCode(roomCode)); // 코드 유일할 때 까지
        GameRoom newRoom = GameRoom.builder()
                .roomCode(roomCode)
                .build();
        gameRoomRepository.save(newRoom);

        return roomCode;
    }

    private String generateRandomRoomCode() {
        // 무작위 문자열 생성 영어 + 숫자 6자리
        int length = 6; // 방 코드 길이
        return new Random().ints(48, 122)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}