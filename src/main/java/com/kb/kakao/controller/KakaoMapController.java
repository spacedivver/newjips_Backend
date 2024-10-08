package com.kb.kakao.controller;

import com.kb.kakao.dto.KakaoMapRequestDTO;
import com.kb.kakao.dto.KakaoMapResponseDTO;
import com.kb.kakao.service.KakaoMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(tags = "Kakao Map", description = "Kakao Map 관련 API")
@RestController
@RequestMapping("/api/kakao")
public class KakaoMapController {

    private final KakaoMapService kakaoMapService;

    @Autowired
    public KakaoMapController(KakaoMapService kakaoMapService) {
        this.kakaoMapService = kakaoMapService;
    }

    @ApiOperation(value = "위치 검색", notes = "경도(x), 위도(y), 쿼리(검색어) 카테고리(CS2 : 편의점, HP8: 병원, BK9:은행, CE7: 카페)를 이용해 위치를 검색합니다.")
    @GetMapping("/convenient")
    public ResponseEntity<KakaoMapResponseDTO> getLocations(
            @RequestParam String x,
            @RequestParam String y,
            @RequestParam String query,
            @RequestParam String category_group_code){

        KakaoMapRequestDTO requestDTO = KakaoMapRequestDTO.builder()
                .query(query)
                .x(x)
                .y(y)
                .category_group_code(category_group_code)
                .build();

        Optional<KakaoMapResponseDTO> responseDTO = kakaoMapService.getLocations(requestDTO);

        return responseDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(500).body(null));
    }
}
