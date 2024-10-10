package com.kb.home.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PopularEstateDTO {
    // Getters와 Setters
    private String address;
    private String tradetype;
    private double roomSize;
    private int floor;
    private long deposit;
    private long monthlyPee;
    private String img;

}