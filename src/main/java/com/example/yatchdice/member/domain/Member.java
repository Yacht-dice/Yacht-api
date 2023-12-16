package com.example.yatchdice.member.domain;

import com.example.yatchdice.authentication.domain.oauth.OAuthProvider;
import com.example.yatchdice.gameroom.domain.GameRoom;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String image;

    private OAuthProvider oAuthProvider;

    @OneToOne(mappedBy = "host", cascade = CascadeType.ALL)
    private GameRoom myGame;

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    private GameRoom otherGame;

    @Builder
    public Member(String email, String nickname, OAuthProvider oAuthProvider, String image) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
        this.image = image;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}