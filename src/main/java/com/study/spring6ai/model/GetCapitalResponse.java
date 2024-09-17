package com.study.spring6ai.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("name of the capital") String answer) {
}
