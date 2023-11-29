package com.example.yatchdice.chatgpt.presentation;

import com.example.yatchdice.chatgpt.application.ChatGptService;
import com.example.yatchdice.chatgpt.dto.ChatGptResponse;
import com.example.yatchdice.chatgpt.dto.QuestionRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chat-gpt")
public class ChatGptController {

    private final ChatGptService chatGptService;

    @Operation(summary = "Question to Chat-GPT")
    @PostMapping("/question")
    public ResponseEntity<?> sendQuestion(@RequestBody QuestionRequest questionRequest) {
        try {
            ChatGptResponse chatGptResponse = chatGptService.askQuestion(questionRequest);
            return ResponseEntity.ok(chatGptResponse);
        } catch (Exception e) {
            // 에러 발생 시 적절한 HTTP 상태 코드와 에러 메시지 반환
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred: " + e.getMessage());
        }
    }


}

