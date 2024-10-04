package com.kb.buddiz.controller;

import com.kb.buddiz.dto.BuddizDTO;
import com.kb.buddiz.service.BuddizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buddiz")
public class BuddizController {

    private final BuddizService buddizService;

    @Autowired
    public BuddizController(BuddizService buddizService) {
        this.buddizService = buddizService;
    }

    @GetMapping("/intro") // Endpoint to get all Buddiz introductions
    public List<BuddizDTO> getAllBuddizIntro() {
        return buddizService.getAllBuddizIntro();
    }

    @GetMapping("/intro/{uno}") // Get Buddiz intro by unique number
    public BuddizDTO getBuddizIntro(@PathVariable Integer uno) {
        return buddizService.getBuddizIntro(uno);
    }

    @PutMapping("/intro/update")
    public String updateBuddizIntro(@RequestBody BuddizDTO buddizDTO) {
        buddizService.updateBuddizIntro(buddizDTO);
        return "Buddiz Introduction updated successfully";
    }
}
