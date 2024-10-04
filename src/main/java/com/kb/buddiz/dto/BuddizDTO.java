package com.kb.buddiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuddizDTO {
    private Integer uno; // Unique number for identification
    private Integer liveInKr;
    private String character;
    private Integer cost;
    private Integer hiredTimes;
    private Double rating;
    private String selfInfo;
    private String lan;
    private Integer reviewcnt;
}
