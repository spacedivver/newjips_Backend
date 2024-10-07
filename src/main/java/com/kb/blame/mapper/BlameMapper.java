package com.kb.blame.mapper;

import com.kb.blame.dto.*;

import java.util.List;

public interface BlameMapper {
    // 신고하기
    int insertBlame(Blame blame);
    Blame selectBlameById(Long blaid);

    // 신고 버디즈, 매물 리스트
    List<Blame> selectBuddizBlameList(long uno);
    List<Blame> selectEstateBlameList(long uno);
}
