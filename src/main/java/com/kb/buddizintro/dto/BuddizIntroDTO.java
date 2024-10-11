package com.kb.buddizintro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuddizIntroDTO {
    private Integer uno; // Unique number for identification
    private Integer liveInKr; // Experience in Korea
    private String personality; // Characteristic or personality field
    private Long cost; // Change to Long for compatibility with bigint
    private Integer hiredTimes; // Number of times hired
    private Double rating; // Rating
    private String selfInfo; // Self introduction
    private String lan; // Language preference (e.g., KR, VN)
    private String location; // Residence
    private String useLan; // Languages spoken as comma-separated string
}
