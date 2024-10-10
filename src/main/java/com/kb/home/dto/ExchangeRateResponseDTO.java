package com.kb.home.dto;

import lombok.Getter;

import java.util.Map;

@Getter
public class ExchangeRateResponseDTO {
    // Getters and setters
    private String base;
    private String date;
    private Map<String, Double> rates;
}