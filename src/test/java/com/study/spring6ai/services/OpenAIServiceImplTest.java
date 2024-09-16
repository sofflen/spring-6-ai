package com.study.spring6ai.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIService openAIService;

    @Test
    void testGetAnswer() {
        String answer = openAIService.getAnswer("Tell me a dad joke");
        assertThat(answer).isNotBlank();
        System.out.println("Got answer:\n" + answer);
    }
}