package com.kb.wish.controller;

import com.kb.wish.dto.Wish;
import com.kb.wish.dto.WishBuddiz;
import com.kb.wish.dto.WishEstate;
import com.kb.wish.service.WishService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wish")
@RequiredArgsConstructor
@Slf4j
@Api(value = "WishController", tags = "위시 정보")

public class WishController {
    public final WishService service;
    
    @PostMapping("")
    public ResponseEntity<Wish> insertWish(@RequestBody Wish wish){
        return ResponseEntity.ok(service.insertWish(wish));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wish> getWish(@PathVariable("id")long wishid){
        return ResponseEntity.ok(service.selectWishById(wishid));
    }

    @GetMapping("/buddizz/{id}")
    public ResponseEntity<List<WishBuddiz>> getWishBuddiz(@PathVariable("id") long uno){
        return ResponseEntity.ok(service.selectBuddizWishList(uno));
    }

    @GetMapping("/estate/{id}")
    public ResponseEntity<List<WishEstate>> getWishEstate(@PathVariable("id") long uno){
        return ResponseEntity.ok(service.selectEstateWishList(uno));
    }
}
