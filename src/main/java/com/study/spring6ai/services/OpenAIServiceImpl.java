package com.study.spring6ai.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String getAnswer(String question) {

        ChatResponse response = chatClient.prompt()
                .user(question)
                .call()
                .chatResponse();

        return response.getResult().getOutput().getContent();
    }
}
