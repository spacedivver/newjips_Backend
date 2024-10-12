package com.kb.buddiz.controller;

import com.amazonaws.services.kms.model.AlreadyExistsException;
import com.kb.buddiz.dto.*;
import com.kb.buddiz.service.BuddizService;
import com.kb.member.dto.Member;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/buddiz")
@RequiredArgsConstructor
@Slf4j
@Api(value = "BuddizController", tags = "버디즈 정보")
@PropertySource({"classpath:/application.properties"})
public class BuddizController {
    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}/buddiz':'${file_save_location_other}/buddiz'}")
    public String BASE_DIR;

    private final BuddizService service;

    @GetMapping("")
    public ResponseEntity<BuddizPageResult> getList(BuddizParam buddizParam) {
        System.out.println(buddizParam);
        BuddizPageResult result = service.getBuddizList(buddizParam);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/review/{uno}")
    public ResponseEntity<ReviewList> getReviewList(@PathVariable long uno) {
        ReviewList result = service.getReviewList(uno);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userDetail/{uno}")
    public ResponseEntity<Buddiz> getById(@PathVariable long uno)
    {
        return ResponseEntity.ok(service.getBuddiz(uno));
    }


    @PostMapping("/reviewAdd/{uno}")
    public ResponseEntity<Buddiz> create(
            BuddizDTO buddizDTO, @PathVariable long uno,
            @AuthenticationPrincipal Member principal) {
        System.out.println("Received uno: " + uno);
        Buddiz buddiz = buddizDTO.toBuddiz();
        buddiz.setUno(uno);
        buddiz.setReviewerUno(principal.getUno());
        System.out.println("Received uno: " + buddiz.getReviewerUno());
//        log.info("ReviewerUno: {}", buddiz.getReviewerUno());
//        log.info("ReviewContent: {}", buddiz.getReviewContent());
        System.out.println(buddiz.getReviewContent());
        System.out.println("testetsetsetsetsetsetsetsetsettsetsetsetsetsetsetsetsetsetse");
//        buddiz.setUno(principal.getUno());
        return ResponseEntity.ok(service.createBuddiz(buddiz));
    }

    @PostMapping("/reviewWish/{uno}")
    public ResponseEntity<Buddiz> reviewWish(
            @PathVariable long uno,
            BuddizDTO buddizDTO,
            @AuthenticationPrincipal Member principal) {
        if (uno == principal.getUno())
            return ResponseEntity.badRequest().build();

        Buddiz buddiz = buddizDTO.toReview();
        buddiz.setUno(principal.getUno());
        buddiz.setWished_id(uno);

        if (!service.checkUnPicked(buddiz))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(service.createWish(buddiz));
    }

    @PutMapping("/{uno}")
    public ResponseEntity<Buddiz> update(@PathVariable long uno, BuddizDTO buddizDTO) {
        Buddiz buddiz = buddizDTO.toBuddiz();
        buddiz.setUno(uno);
        return ResponseEntity.ok(service.updateBuddiz(buddiz));
    }

    @DeleteMapping("/{uno}")
    public ResponseEntity<Buddiz> delete(@PathVariable long uno) {
        return ResponseEntity.ok(service.deleteBuddiz(uno));
    }

}
