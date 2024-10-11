package com.kb.wish.mapper;

import com.kb.wish.dto.Wish;
import com.kb.wish.dto.WishBuddiz;
import com.kb.wish.dto.WishEstate;

import java.util.List;

public interface WishMapper {
    //찜 등록하기
    int insertWish(Wish wish);
    Wish selectWishById(Long wishid);

    //찜 버디즈,매물 리스트
    List<WishBuddiz> selectBuddizWishList(long uno);
    List<WishEstate> selectEstateWishList(long uno);
}
