package com.kb.home.controller;

import com.kb.home.dto.PopularBuddizDTO;
import com.kb.home.service.PopularBuddizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PopularBuddizController {

    @Autowired
    private PopularBuddizService buddyService;

    @GetMapping("/popular-buddies")
    public ResponseEntity<List<PopularBuddizDTO>> getPopularBuddies() {
        List<PopularBuddizDTO> buddies = buddyService.getTopBuddies();

        if (buddies == null || buddies.isEmpty()) {
            return ResponseEntity.noContent().build();  // 결과가 없을 경우 204 상태 반환
        }

        return ResponseEntity.ok(buddies);  // 결과가 있을 경우 200 상태와 함께 결과 반환
    }
}
