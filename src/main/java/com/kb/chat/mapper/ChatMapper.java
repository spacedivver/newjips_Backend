package com.kb.chat.mapper;

import com.kb.chat.dto.*;

import java.util.List;

public interface ChatMapper {
    // 채팅방
    int insertChatRoom(ChatRoom chatRoom);
    ChatRoom getChatRoomById(Long roomId);
    boolean checkChatRoomFromExist(ChatRoom chatRoom);
    boolean checkChatRoomToExist(ChatRoom chatRoom);
    List<ChatRoom> getAllChatRooms(Long uno);
    int updateChatRoom(ChatRoom chatRoom);
    int updateChatRoomForSelect(ChatRoom chatRoom);

    // 쪽지
    int insertChatMsg(ChatMsg chatMsg);

    // 채팅 내용
    List<ChatMsg> getChatMsgByRoomId(Long roomId);
}
