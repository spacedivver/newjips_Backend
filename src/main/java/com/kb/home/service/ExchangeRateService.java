package com.kb.home.service;

import com.kb.home.dto.ExchangeRateResponseDTO;
import com.kb.home.mapper.ExchangeRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    private static final String API_KEY = "ghDJfZxc9SxlA63MGXGK1WebuKEbSQRK";
    private static final String EXCHANGE_RATE_API_URL = "https://api.apilayer.com/fixer/latest";

    public String getExchangeRate(String symbols, String base) {
        // 외부 API 호출 URL 생성
        String uri = UriComponentsBuilder.fromHttpUrl(EXCHANGE_RATE_API_URL)
                .queryParam("symbols", symbols)
                .queryParam("base", base)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("apikey", API_KEY);

        // API 호출
        org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<>(headers);
        org.springframework.http.ResponseEntity<String> response = restTemplate.exchange(uri, org.springframework.http.HttpMethod.GET, entity, String.class);

        // API 응답 반환
        return response.getBody();
    }
}