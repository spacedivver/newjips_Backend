package com.kb.wish.dto;

import com.kb.blame.dto.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class WishEstate {
    private long wishid;
    private long uno; //찜 등록한 유저
    private long wishedId; //찜 당한 유저
    private String content;
    private TargetType targetType;
    private Date datetime;

    private String tradetype;
    private String deposit;
    private String monthlyPee;
    private String img;
}
