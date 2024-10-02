package com.kb.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatMsg {
    private long id;
    private long roomId;
    private String content;
    private boolean isFromSender;
    private Date createAt;
}
