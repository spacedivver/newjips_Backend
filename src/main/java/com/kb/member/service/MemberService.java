package com.kb.member.service;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.member.dto.Member;
import com.kb.member.exception.PasswordMissmatchException;
import com.kb.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class MemberService{

    @Value("#{'${os_type}' == 'win' ? '${file_save_location_win}':'${file_save_location_other}'}")
    public String LOCATION;

    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;
    final S3Uploader s3Uploader;
    final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    final String default_img = "https://cdn-nj.s3.ap-northeast-2.amazonaws.com/default.jpg";

    public Member login(Member member) {
        Member saveMember = mapper.selectById(member.getUserId());
        if(passwordEncoder.matches(member.getPassword(), saveMember.getPassword())) {
            saveMember.setPassword(""); //비밀번호 제거
            saveMember.setUno(0); //회원 번호 초기화
            return saveMember;
        }else{
            return null; //인증 실패
        }
    }

    public boolean checkDuplicate(String userId) {
        Member member = mapper.selectById(userId);
        return member != null; //중복 확인
    }

    public boolean isBuddiz(long uno){
        Long member = mapper.findBuddiz(uno);
        return member != null; //null이면 false, 있으면 true
    }

    public Member getMember(String userId) {
        return Optional.ofNullable(mapper.selectById(userId))
                        .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public Member join(Member member, MultipartFile avatar) throws IllegalAccessException {
        if(member.checkRequiredValue()){
            throw new IllegalAccessException("필수 값이 누락되었습니다."); //예외 처리
        }
        member.setPassword(passwordEncoder.encode(member.getPassword())); //비밀번호 암호화
        member.setProfilePic(default_img);

        int result = mapper.insertMember(member);
        if(result != 1){
            throw new IllegalAccessException("회원 가입에 실패했습니다."); //예외 처리
        }

        return mapper.selectById(member.getUserId());
    }

    public Member update(Member updateMember, MultipartFile file) throws IllegalAccessException {
        Member oldMember = mapper.selectByUno(updateMember.getUno());
        updateMember.setUno(oldMember.getUno());
        if(file != null && !file.isEmpty()) {
            if (file.getSize() > MAX_FILE_SIZE) {
                throw new IllegalArgumentException("파일 크기는 최대 5MB 입니다.");
            }

            if (!oldMember.getProfilePic().equals(default_img)) {
                s3Uploader.deleteFile(oldMember.getProfilePic());
            }

            // 이미지 업로드
            String url = s3Uploader.saveFile(file);
            updateMember.setProfilePic(url);
        } else {
            updateMember.setProfilePic(default_img);
        }
        mapper.updateMember(updateMember);
        return mapper.selectByUno(updateMember.getUno());
    }

    public void changePassword(ChangePasswordDTO changePassword) {
        Member member = mapper.selectByUno(changePassword.getUno());

        if(!passwordEncoder.matches(
            changePassword.getOldPassword(),
            member.getPassword()
        )) {
              throw new PasswordMissmatchException();
        }

        member.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        int result = mapper.updatePassword(member);
        if(result != 1){
            throw new NoSuchElementException();
        }
    }
}
