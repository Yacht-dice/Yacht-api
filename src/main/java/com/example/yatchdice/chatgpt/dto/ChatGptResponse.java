package com.example.yatchdice.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.theokanning.openai.Usage;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class ChatGptResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Usage usage;
    private List<Choice> choices;

    @Getter
    @Setter
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    }
    @Getter
    @Setter
    public static class Choice {
        private ChatGptMessage message;
        @JsonProperty("finish_reason")
        private String finishReason;
        private int index;
    }
}
