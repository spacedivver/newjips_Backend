package com.kb.buddiz.mapper;

import com.kb.buddiz.dto.Buddiz;
import com.kb.buddiz.dto.BuddizParam;
import com.kb.buddiz.dto.Review;

import java.util.List;

public interface BuddizMapper {
    int selectBuddizCount(BuddizParam param);
    List<Buddiz> selectBuddizList(BuddizParam param);
    Buddiz selectBuddizByUno(long uno);
    List<Review> selectReviewByUno(long uno);
    int insertBuddiz(Buddiz buddiz);
    int updateBuddiz(Buddiz buddiz);
    int deleteBuddiz(long uno);
    int selectReviewCount(long uno);
    int insertWish(Buddiz buddiz);
    double selectReviewAvg(long uno);

    int checkPicked(Buddiz buddiz);
}
