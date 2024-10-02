package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;

import java.util.List;

public interface MemberMapper {
//    List<Member> selectMemberAll(); //전체 회원 조회 메서드
    Member selectById(String id); //ID로 회원 조회
    int insertMember(Member member); //회원 추가
    int updateMember(Member member); //회원 정보 업데이트
    int updatePassword(ChangePasswordDTO changePasswordDTO); //비밀번호 업데이트
    int deleteMember(long mno); //회원 삭제
//    int insertAuth(Auth auth); //인증 추가 메서드
    int deleteAuth(Auth auth); //인증 삭제
}
