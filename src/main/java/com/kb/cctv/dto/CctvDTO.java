package com.kb.cctv.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CctvDTO {
    private Long cid;
    private Double latitude;
    private Double longitude;
}
