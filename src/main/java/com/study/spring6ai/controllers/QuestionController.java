package com.study.spring6ai.controllers;

import com.study.spring6ai.model.Answer;
import com.study.spring6ai.model.GetCapitalRequest;
import com.study.spring6ai.model.Question;
import com.study.spring6ai.services.OpenAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;

    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @GetMapping("/capital")
    public Answer getCapital(@RequestBody GetCapitalRequest request) {
        return openAIService.getCapital(request);
    }
}
