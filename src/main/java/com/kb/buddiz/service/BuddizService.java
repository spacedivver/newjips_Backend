package com.kb.buddiz.service;

import com.kb.buddiz.dto.BuddizDTO;
import com.kb.buddiz.mapper.BuddizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddizService {

    @Autowired
    private BuddizMapper buddizMapper;

    public List<BuddizDTO> getAllBuddizIntro() { // New method to fetch all introductions
        return buddizMapper.getAllBuddizIntro();
    }

    public BuddizDTO getBuddizIntro(Integer uno) { // Get Buddiz introduction by unique number
        return buddizMapper.getBuddizIntro(uno);
    }

    public void updateBuddizIntro(BuddizDTO buddizDTO) {
        buddizMapper.updateBuddizIntro(buddizDTO);
    }
}
