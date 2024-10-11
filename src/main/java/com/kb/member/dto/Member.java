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
@Builder
public class Member implements UserDetails {
    private long uno;			// userPK
    private String userId; 		// userId=username
    private String password;	// password
    private String name;        // 사용자이름
    private String nickname;    // 닉네임
    private String gender;      // 성별
    private String status = "Y"; 		// 활성화 여부, Y, N
    private String email;       // 이메일
    private String profilePic;  // 아바타이미지
    private Date createDate;    // 생성일
    private Date modifyDate;    // 수정일



    private String token; // JWT 토큰값, DB로는 저장하지 않음

    // 복수개의 권한을 관리
    // 문자열data("ROLE_USER", "ROLE_ADMIN")를 처리할 수 있는 GrantedAuthority의 하위클래스
    private List<SimpleGrantedAuthority> authorities; // authorities

    /**
     * Collection - List/Set
     *
     * Collection<? extends GrantedAuthority>
     * 	- <GrantedAuthority를 상속하는 ?> -> 자식타입(상한선)
     *  - <? super Integer> -> Integer 부모타입 (하한선)
     * Collection<GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String setUsername(String userId) {
        return this.userId = userId;
    }
    @Override
    public String getUsername() {
        return userId;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equalsIgnoreCase("Y");
    }

    public boolean checkRequiredValue(){
        try {
            return (userId.isEmpty() || password.isEmpty());
        }catch (Exception e){
            return false;
        }
    }

}
