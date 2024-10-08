package com.kb.blame.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BlameEstate {
    private long blaid;
    private long uno;   // 신고한 유저
    private long blamedId;  // 신고당한 id
    private TargetType targetType;
    private String content;
    private Date datetime;

    private String tradetype;
    private String deposit;
    private String monthlyPee;
    private String img;

}
