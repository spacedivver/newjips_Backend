package com.kb.wish.dto;

import com.kb.blame.dto.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WishBuddiz {
    private long wishid;
    private long uno; //찜 등록한 유저
    private long wishedId; //찜 당한 유저
    private TargetType targetType;
    private String content;
    private Date datetime;

    private String wishNickname;
    private String wishProfilePic;
    private String wishPersonality;
}
