package com.kb.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    long uno; //사용자 id
    String oldPassword; // 현재 비밀번호
    String newPassword; // 새로운 비밀번호
}
