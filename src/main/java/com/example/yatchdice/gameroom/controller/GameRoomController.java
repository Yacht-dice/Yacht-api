package com.example.yatchdice.gameroom.controller;

import com.example.yatchdice.gameroom.domain.GameRoom;
import com.example.yatchdice.gameroom.repository.GameRoomRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Tag(name = "rooms", description = "게임룸 관련 API")
@Slf4j
public class GameRoomController {
    private final GameRoomRepository gameRoomRepository;

    // 방 코드 생성 후 주는거
    @Operation(summary = "방 코드 생성하기", description = """
            방 입장 전 화면에서 사용자는 닉네임 입력하고 join 또는 host 버튼을 눌러 방에 입장을 할 것입니다.
            
            사용자가 host 버튼을 눌러 방 생성을 하려면, 방코드가 필요할 것입니다.
            
            이 api를 먼저 호출하여 응답받은 방코드를 얻은 뒤, 해당 방 코드를 이용해 websocket에 subscribe 해주세요.
            """)
    @GetMapping("/code")
    @CrossOrigin(origins = "*",maxAge = 3600)
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