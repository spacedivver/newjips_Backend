package com.kb.blame.controller;

import com.kb.blame.dto.Blame;
import com.kb.blame.dto.BlameBuddiz;
import com.kb.blame.dto.BlameEstate;
import com.kb.blame.service.BlameService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blame")
@RequiredArgsConstructor
@Slf4j
@Api(value = "BlameController", tags = "신고 정보")
public class BlameController {
    private final BlameService service;

    @PostMapping("")
    public ResponseEntity<Blame> insertBlame(@RequestBody Blame blame) {
        return ResponseEntity.ok(service.insertBlame(blame));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blame> getBlame(@PathVariable("id") long blaid) {
        return ResponseEntity.ok(service.selectBlameById(blaid));
    }

    @GetMapping("/buddiz/{id}")
    public ResponseEntity<List<BlameBuddiz>> getBlameBuddiz(@PathVariable("id") long uno) {
        return ResponseEntity.ok(service.selectBuddizBlameList(uno));
    }

    @GetMapping("/estate/{id}")
    public ResponseEntity<List<BlameEstate>> getBlameEstate(@PathVariable("id") long uno) {
        return ResponseEntity.ok(service.selectEstateBlameList(uno));
    }
}