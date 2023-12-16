package com.example.yatchdice.gameroom.repository;

import com.example.yatchdice.gameroom.domain.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {


    boolean existsByRoomCode(String roomCode);
}
