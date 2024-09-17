package com.study.spring6ai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring6ai.model.Answer;
import com.study.spring6ai.model.GetCapitalRequest;
import com.study.spring6ai.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;
    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    public OpenAIServiceImpl(ChatClient.Builder chatClientBuilder, ObjectMapper objectMapper) {
        this.chatClient = chatClientBuilder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public String getAnswer(String question) {

        ChatResponse response = chatClient.prompt()
                .user(question)
                .call()
                .chatResponse();

        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {

        ChatResponse response = chatClient.prompt()
                .user(question.question())
                .call()
                .chatResponse();

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getCapital(GetCapitalRequest request) {
        var promptTemplate = new PromptTemplate(getCapitalPrompt);
        var prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));

        ChatResponse response = chatClient
                .prompt(prompt)
                .call()
                .chatResponse();

        String responseContent = response.getResult().getOutput().getContent();
        System.out.println(responseContent);

        String responseJson;
        try {
            JsonNode jsonNode = objectMapper.readTree(responseContent);
            responseJson = jsonNode.get("answer").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error during processing of responseContent", e);
        }

        return new Answer(responseJson);
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest request) {
        var promptTemplate = new PromptTemplate(getCapitalWithInfoPrompt);
        var prompt = promptTemplate.create(Map.of("stateOrCountry", request.stateOrCountry()));

        ChatResponse response = chatClient
                .prompt(prompt)
                .call()
                .chatResponse();

        return new Answer(response.getResult().getOutput().getContent());
    }
}
