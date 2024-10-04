package com.kb.buddiz.mapper;

import com.kb.buddiz.dto.BuddizDTO;
import java.util.List;

public interface BuddizMapper {
    List<BuddizDTO> getAllBuddizIntro(); // Get all Buddiz introductions

    BuddizDTO getBuddizIntro(Integer uno); // Get Buddiz introduction by unique number

    void updateBuddizIntro(BuddizDTO buddizDTO); // Update Buddiz introduction
}
