package com.kb.member.controller;

import com.kb.common.util.UploadFiles;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Api(value = "MemberController", tags = "멤버 정보")
@PropertySource({"classpath:/application.properties"})
public class MemberController {

    private final MemberService service;

    @GetMapping("/checkid/{userId}")
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok().body(service.checkDuplicate(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Member> get(@PathVariable String userId) {
        Member member = service.getMember(userId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/buddiz/{uno}")
    public ResponseEntity<Boolean> get(@PathVariable long uno){
        boolean member = service.isBuddiz(uno);
            return ResponseEntity.ok(member);
    }

    @PostMapping("")
    public ResponseEntity<Member> join(MemberDTO memberDTO,
                                       @RequestParam(name = "avatar", required = false) MultipartFile avatar) throws IllegalAccessException {
        try {
            Member member = memberDTO.toMember();

            // 성별 초기화
            if (member.getGender() == null || member.getGender().isEmpty()) {
                member.setGender("M"); // 기본값으로 남성 설정
            }

            // 닉네임 기본값 설정
            // 닉네임 없거나 빈 문자열(사용자가 닉네임을 제공했지만 빈 문자열을 입력한 경우)일 경우
            if (member.getNickname() == null || member.getNickname().isEmpty()) {
                member.setNickname("Guest"); // 기본값 설정
            }

            member.setProfilePic("/avatar/unknown.png"); // 기본 이미지
            if (avatar != null && !avatar.isEmpty()) {
                // 업로드된 이미지 처리 로직
                // 예: member.setProfilePic("/avatar/" + savedImageName);
            }

            Member createdMember = service.join(member, avatar);
            return ResponseEntity.status(201).body(createdMember);
        } catch (IllegalAccessException e) {
            log.error("회원 가입 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.badRequest().build(); // 적절한 오류 코드 반환
        }
//        return ResponseEntity.ok(service.join(member, avatar));
    }


    @PutMapping("/changepassword")
    public ResponseEntity<?> changePassword(ChangePasswordDTO changePassword) {
        service.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<Member> changeProfile(MemberDTO memberDTO,
                @RequestParam(name = "avatar", required = false) MultipartFile avatar) throws IllegalAccessException {
        Member member = memberDTO.toMember();
        return ResponseEntity.ok(service.update(member, avatar));
    }
}
