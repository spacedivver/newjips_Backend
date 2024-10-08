package com.kb.member.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder // Builder 패턴을 사용하려면 이 애너테이션을 추가합니다.
public class MemberDTO {
    private long uno;          // Primary Key
    private String userId; 		// id=username
    private String password;	// password
    private String name;        // 사용자이름
    private String nickname;    // 닉네임
    private String gender;      // 성별
    private String profilePic;  // 이미지

    public Member toMember() {
        return Member.builder().uno(uno).userId(userId).password(password).name(name).nickname(nickname).gender(gender).profilePic(profilePic).build();
    }
}
