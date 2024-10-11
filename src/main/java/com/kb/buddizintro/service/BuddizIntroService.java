package com.kb.buddizintro.service;

import com.kb.buddizintro.dto.BuddizIntroDTO;
import com.kb.buddizintro.mapper.BuddizIntroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuddizIntroService {

    private final BuddizIntroMapper buddizMapper;

    @Autowired
    public BuddizIntroService(BuddizIntroMapper buddizMapper) {
        this.buddizMapper = buddizMapper;
    }

    public List<BuddizIntroDTO> getAllBuddizIntro() { // Fetch all introductions
        return buddizMapper.getAllBuddizIntro();
    }

    public BuddizIntroDTO getBuddizIntro(Integer uno) { // Fetch Buddiz intro by unique ID
        return buddizMapper.getBuddizIntro(uno);
    }

    public void saveOrUpdateBuddizIntro(BuddizIntroDTO buddizDTO) {
        // Check if a profile already exists
        BuddizIntroDTO existingProfile = buddizMapper.getBuddizIntro(buddizDTO.getUno());
        if (existingProfile != null) {
            // Update the existing profile
            buddizMapper.updateBuddizIntro(buddizDTO);
        } else {
            // Insert a new profile
            buddizMapper.insertBuddizIntro(buddizDTO);
        }
    }
}
