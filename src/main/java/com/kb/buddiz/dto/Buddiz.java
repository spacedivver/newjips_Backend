package com.kb.buddiz.dto;

import com.kb.board.dto.BoardAttachFile;
import com.kb.board.dto.BoardReply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Buddiz {
    private long uno;
    private long liveInKr;
    private String personality;
    private long cost;
    private long hiredTimes;
    private long rating;
    private String selfInfo;
    private String lan;
    private long reviewcnt;
    private String location;
    private long wished_id; // 찜 대상 uno
//    private long likeUno; // 찜을 수행한 uno
    private String profilePic;
    private double avg;


    // 외부 조인
    private String gender;
    private String userId;
    private String name;
    private String nickname;
    private String reviewer;
    private String reviewContent;
    private long reviewerUno;

    private ArrayList<Buddiz> ReviewList;

}
