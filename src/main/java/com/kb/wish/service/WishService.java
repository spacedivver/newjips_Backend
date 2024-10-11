package com.kb.wish.service;

import com.kb.wish.dto.Wish;
import com.kb.wish.dto.WishBuddiz;
import com.kb.wish.dto.WishEstate;
import com.kb.wish.mapper.WishMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishService {
    public final WishMapper mapper;

    @Transactional
    public Wish insertWish(Wish wish){
        log.info("Wish: Insert Wish.................." );
        int result = mapper.insertWish(wish);
        if(result != 1){
            throw new NoSuchElementException();
        }
        return selectWishById(wish.getWishedId());
    }

    @Transactional
    public Wish selectWishById(long wishedId){
        log.info("Wish: Select Wish..................");
        return mapper.selectWishById(wishedId);
    }
    
    @Transactional
    public List<WishBuddiz> selectBuddizWishList(long uno){
        log.info("Wish: Select Buddiz Wish..................");
        return mapper.selectBuddizWishList(uno);
    }

    @Transactional
    public List<WishEstate> selectEstateWishList(long uno){
        log.info("Wish: Select Estate Wish..................");
        return mapper.selectEstateWishList(uno);
    }
}
