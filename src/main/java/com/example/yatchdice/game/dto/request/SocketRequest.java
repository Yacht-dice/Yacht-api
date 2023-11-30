package com.example.yatchdice.game.dto.request;

import com.example.yatchdice.game.Status;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SocketRequest {
    private String senderName;
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
