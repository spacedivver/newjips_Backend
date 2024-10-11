package com.kb.buddizintro.controller;

import com.kb.buddizintro.dto.BuddizIntroDTO;
import com.kb.buddizintro.service.BuddizIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buddiz")
@CrossOrigin(origins = "http://localhost:5173") // Enable CORS for Vue frontend
public class BuddizIntroController {

    private final BuddizIntroService buddizService;

    @Autowired
    public BuddizIntroController(BuddizIntroService buddizService) {
        this.buddizService = buddizService;
    }

    @GetMapping("/intro") // Endpoint to get all Buddiz introductions
    public List<BuddizIntroDTO> getAllBuddizIntro() {
        return buddizService.getAllBuddizIntro();
    }

    @GetMapping("/intro/{uno}") // Get Buddiz intro by unique number
    public BuddizIntroDTO getBuddizIntro(@PathVariable Integer uno) {
        return buddizService.getBuddizIntro(uno);
    }

    @PostMapping("/intro/saveOrUpdate")
    public String saveOrUpdateBuddizIntro(@RequestBody BuddizIntroDTO buddizDTO) {
        if (buddizDTO.getLocation() == null || buddizDTO.getLocation().isEmpty()) {
            return "Error: Location is required";
        }

        if (buddizDTO.getUseLan() == null || buddizDTO.getUseLan().isEmpty()) {
            return "Error: Languages spoken (useLan) is required";
        }

        try {
            buddizService.saveOrUpdateBuddizIntro(buddizDTO);
            return "Buddiz Introduction saved successfully";
        } catch (Exception e) {
            System.err.println("Error updating Buddiz intro: " + e.getMessage());
            e.printStackTrace();
            return "Error updating Buddiz intro: " + e.getMessage();
        }
    }


}
