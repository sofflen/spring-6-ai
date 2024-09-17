package com.study.spring6ai.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(@JsonPropertyDescription("capital name") String capital,
                                         @JsonPropertyDescription("population of the capital") String population,
                                         @JsonPropertyDescription("region location of the capital") String region,
                                         @JsonPropertyDescription("national language") String language,
                                         @JsonPropertyDescription("national currency") String currency) {
}
