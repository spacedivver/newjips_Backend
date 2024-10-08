package com.kb.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoMapRequestDTO {
    private String query;
    private String x;
    private String y;
    private String category_group_code;
}
