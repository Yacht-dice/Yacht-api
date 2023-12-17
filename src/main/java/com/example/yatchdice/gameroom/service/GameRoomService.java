package com.example.yatchdice.gameroom.service;

import com.example.yatchdice.gameroom.dto.request.CheckRoomRequest;
import com.example.yatchdice.gameroom.dto.response.CheckRoomResponse;
import com.example.yatchdice.gameroom.repository.GameRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameRoomService {
    private final GameRoomRepository gameRoomRepository;


    public CheckRoomResponse checkRoom(CheckRoomRequest checkRoomRequest) {
        return CheckRoomResponse.of(gameRoomRepository.existsByRoomCode(checkRoomRequest.getRoomCode()));
    }
}
