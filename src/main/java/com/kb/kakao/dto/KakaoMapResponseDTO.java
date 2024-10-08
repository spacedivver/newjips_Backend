package com.kb.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoMapResponseDTO {
    private MetaDTO meta;
    private String contentType;
    private List<DocumentDTO> documents; // 위도와 경도로 이루어진 리스트

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DocumentDTO {
        private String x;
        private String y;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MetaDTO {
        private int total_count;
    }
}
