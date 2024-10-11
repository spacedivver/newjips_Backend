package com.kb.buddizintro.mapper;

import com.kb.buddizintro.dto.BuddizIntroDTO;
import java.util.List;

public interface BuddizIntroMapper {
    List<BuddizIntroDTO> getAllBuddizIntro(); // Get all Buddiz introductions

    BuddizIntroDTO getBuddizIntro(Integer uno); // Get Buddiz introduction by unique number

    void updateBuddizIntro(BuddizIntroDTO buddizIntroDTO); // Update Buddiz introduction

    void insertBuddizIntro(BuddizIntroDTO buddizDTO); // Insert a new Buddiz introduction
}
