package com.kb.estate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstateDTO {
    private Long eno;
    private String address;
    private String housetype;
    private Long monthlyPee;
    private String tradetype;
    private Long deposit;
    private Double latitude;
    private Double longitude;
    private String option;
    private String secOption;
    private Long floor;
    private Double roomSize;
    private String distToSub;
    private String lan;
    private Long mno;
    private String content;
    private String imgString;
    private List<String> img;
}
