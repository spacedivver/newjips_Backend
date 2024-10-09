package com.kb.kakao.service;

import com.kb.kakao.dto.KakaoMapRequestDTO;
import com.kb.kakao.dto.KakaoMapResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class KakaoMapService {

    private static final String KAKAO_API_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    @Value("${kakao.api.key}")
    private String REST_API_KEY;

    private static final Logger log = LoggerFactory.getLogger(KakaoMapService.class);

    @Autowired
    private RestTemplate restTemplate; // RestTemplate 주입

    public Optional<KakaoMapResponseDTO> getLocations(KakaoMapRequestDTO request) {
        String url = String.format("%s?query=%s&y=%s&x=%s&radius=%d&category_group_code=%s",
                KAKAO_API_URL,
                request.getQuery(),
                request.getY(),
                request.getX(), 3000,request.getCategory_group_code());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + REST_API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<KakaoMapResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoMapResponseDTO.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                KakaoMapResponseDTO responseBody = responseEntity.getBody();
                // contentType 설정
                if (responseEntity.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE)) {
                    responseBody.setContentType(responseEntity.getHeaders().get(HttpHeaders.CONTENT_TYPE).get(0));
                }
                return Optional.ofNullable(responseBody);
            } else {
                log.error("API 호출 실패: {}", responseEntity.getStatusCode());
                return Optional.empty();
            }
        } catch (RestClientException e) {
            log.error("API 호출 중 오류 발생", e);
            return Optional.empty();
        }
    }
}
