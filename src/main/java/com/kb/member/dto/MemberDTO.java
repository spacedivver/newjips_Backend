package com.kb.member.dto;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder // Builder 패턴을 사용하려면 이 애너테이션을 추가합니다.
public class MemberDTO {
    private Long uno;          // Primary Key
    private String userId; 		// id=username
    private String password;	// password
    private String name;        // 사용자이름
    private String nickname;    // 닉네임
    private String gender;      // 성별

    public Member toMember() {
        return Member.builder().userId(userId).password(password).name(name).nickname(nickname).gender(gender).build();
    }
}
