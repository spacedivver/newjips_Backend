package com.kb.buddizintro.service;


import com.kb.buddizintro.dto.BuddizIntroDTO;
import com.kb.buddizintro.mapper.BuddizIntroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddizIntroService {

    @Autowired
    private BuddizIntroMapper buddizMapper;

    public List<BuddizIntroDTO> getAllBuddizIntro() { // New method to fetch all introductions
        return buddizMapper.getAllBuddizIntro();
    }

    public BuddizIntroDTO getBuddizIntro(Integer uno) { // Get Buddiz introduction by unique number
        return buddizMapper.getBuddizIntro(uno);
    }

    public void updateBuddizIntro(BuddizIntroDTO buddizDTO) {
        buddizMapper.updateBuddizIntro(buddizDTO);
    }
}
