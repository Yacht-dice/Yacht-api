package com.example.yatchdice.gameroom.domain;

import com.example.yatchdice.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Entity
@NoArgsConstructor
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomCode;

    @OneToOne
    @JoinColumn(name = "host_id")
    private Member host;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private Member guest;

    @Builder
    public GameRoom(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setHost(Member host) {
        this.host = host;
    }

    public void setGuest(Member guest) {
        this.guest = guest;
    }
}