package com.study.spring6ai.services;

import com.study.spring6ai.model.Answer;
import com.study.spring6ai.model.GetCapitalRequest;
import com.study.spring6ai.model.GetCapitalResponse;
import com.study.spring6ai.model.GetCapitalWithInfoResponse;
import com.study.spring6ai.model.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest request);

    GetCapitalWithInfoResponse getCapitalWithInfo(GetCapitalRequest request);
}
