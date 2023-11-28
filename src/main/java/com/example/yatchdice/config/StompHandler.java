package com.example.yatchdice.config;

import com.example.yatchdice.exception.badrequest.BadRequestException;
import com.example.yatchdice.exception.badrequest.BadRequestTokenException;
import com.example.yatchdice.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        log.info("sdf");
        // websocket 연결시 헤더의 jwt token 유효성 검증
        if (StompCommand.CONNECT == accessor.getCommand()) {
            final String authorization = jwtTokenProvider.extractJwt(accessor);

            if(!jwtTokenProvider.validToken(authorization))
                throw new BadRequestTokenException();
        }
        return message;
    }
}