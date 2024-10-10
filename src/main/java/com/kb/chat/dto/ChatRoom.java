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
public class ChatRoom {
    private long cno;
    private long fromId;
    private String fromName;
    private String fromImg;
    private long toId;
    private String toName;
    private String toImg;
    private String content;
    private int senderUnreadCount;
    private int receiverUnreadCount;
    private Date createAt;
    private Date updateAt;
    private boolean isRequesterFrom;
}
