package com.kb.home.mapper;

import com.kb.home.dto.ExchangeRateResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ExchangeRateMapper {

    public ExchangeRateResponseDTO toDto(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(responseBody, ExchangeRateResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}