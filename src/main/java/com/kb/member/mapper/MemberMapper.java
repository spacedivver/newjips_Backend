package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.ChangePasswordDTO;
import com.kb.member.dto.Member;

import java.util.List;

public interface MemberMapper {
    List<Member> selectMemberAll(); //전체 회원 조회 메서드
    Member selectById(String userId); //ID로 회원 조회
    Member selectByUno(long uno); //uno로 회원 조회
    int insertMember(Member member); //회원 추가
    int updateMember(Member member); //회원 정보 업데이트
    int updatePassword(Member member); //비밀번호 업데이트
    int deleteAuth(Auth auth); //인증 삭제
    Long findBuddiz(long uno);
}
