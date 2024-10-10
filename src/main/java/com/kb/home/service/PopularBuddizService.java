package com.kb.home.service;

import com.kb.home.dto.PopularBuddizDTO;
import com.kb.home.mapper.PopularBuddizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopularBuddizService {

    @Autowired
    private PopularBuddizMapper buddizMapper;

    public List<PopularBuddizDTO> getTopBuddies() {
        List<PopularBuddizDTO> results = buddizMapper.findTopBuddiz();

        if (results == null || results.isEmpty()) {
            return new ArrayList<>(); // 결과가 없을 경우 빈 리스트 반환
        }

        return results;  // 결과 리스트 반환
    }
}