package com.kb.hotplace.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotplaceDTO {
    private Long hno;
    private String hpName;
    private Double longitude;
    private Double latitude;
    private String lan;
}
