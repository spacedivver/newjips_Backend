package com.kb.chat.service;

import com.kb._config.RootConfig;
import com.kb.chat.dto.ChatMsg;
import com.kb.chat.dto.ChatRoom;
import com.kb.chat.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatMapper mapper;

    public List<ChatRoom> getChatRooms(Long uno) {
        List<ChatRoom> chatRooms = mapper.getAllChatRooms(uno);
        if (!chatRooms.isEmpty()) {
            chatRooms.forEach(chatRoom -> {
               chatRoom.setRequesterFrom(chatRoom.getFromId() == uno);
            });
        }

        return chatRooms;
    }

    @Transactional(rollbackFor = Exception.class)
    public ChatRoom createChatRoom(ChatRoom chatRoom) {
        log.info("Chat : createChatRoom...........");
        // 내가 만든 방이 이미 있는지
        if (mapper.checkChatRoomFromExist(chatRoom))
            throw new NoSuchElementException("Chat room already exist");

        // 내가 초대된 방이 이미 있는지
        if (mapper.checkChatRoomToExist(chatRoom))
            throw new NoSuchElementException("Chat room already exist");

        int result = mapper.insertChatRoom(chatRoom);
        chatRoom = mapper.getChatRoomById(chatRoom.getCno());
        return chatRoom;
    }

    public ChatRoom getChatRoomByCno(long cno) {
        log.info("Chat : getChatRoomByCno...........");
        ChatRoom chatRoom = mapper.getChatRoomById(cno);
        if (chatRoom == null)
            throw new NoSuchElementException("Chat room not found");
        return chatRoom;
    }

    @Transactional
    public ChatRoom updateChatRoom(ChatRoom chatRoom) {
        log.info("Chat : updateChatRoom...........");
        int result = mapper.updateChatRoom(chatRoom);
        if (result != 1)
            throw new NoSuchElementException("failed to update chat room");
        return getChatRoomByCno(chatRoom.getCno());
    }

    @Transactional
    public ChatRoom updateChatRoomForSelect(ChatRoom chatRoom) {
        log.info("Chat : updateChatRoom...........");
        int result = mapper.updateChatRoomForSelect(chatRoom);
        if (result != 1)
            throw new NoSuchElementException("failed to update chat room");
        return getChatRoomByCno(chatRoom.getCno());
    }

    @Transactional(rollbackFor = Exception.class)
    public ChatRoom sendMsg(ChatMsg chatMsg) {
        log.info("Chat : sendMsg............");

        int result = mapper.insertChatMsg(chatMsg);
        if (result != 1)
            throw new NoSuchElementException("failed to send msg");

        // 채팅방 상태 변경 - 안읽은 메시지 처리
        ChatRoom chatRoom = getChatRoomByCno(chatMsg.getRoomId());

        if (chatMsg.isFromSender()) {   // Sender가 보낸 쪽지
            chatRoom.setReceiverUnreadCount(chatRoom.getReceiverUnreadCount() + 1);
            chatRoom.setSenderUnreadCount(0);
            chatRoom.setContent(chatMsg.getContent());
        } else {    // Receiver가 보낸 쪽지
            chatRoom.setReceiverUnreadCount(0);
            chatRoom.setSenderUnreadCount(chatRoom.getSenderUnreadCount() + 1);
            chatRoom.setContent(chatMsg.getContent());
        }
        updateChatRoom(chatRoom);

        return getChatRoomByCno(chatRoom.getCno());
    }

    public List<ChatMsg> getAllChatMsg(Long roomId, long uno) {
        log.info("Chat : getAllChatMsg............");
        List<ChatMsg> chatMsgList = mapper.getChatMsgByRoomId(roomId);
        if (!chatMsgList.isEmpty()) {
            // 채팅방 상태 변경 - 읽음 처리
            ChatRoom chatRoom = mapper.getChatRoomById(roomId);
            if (chatRoom.getFromId() == uno) {  // Sender가 요청
                chatRoom.setSenderUnreadCount(0);
            } else if (chatRoom.getToId() == uno) { // Receiver가 요청
                chatRoom.setReceiverUnreadCount(0);

            } else {
                throw new NoSuchElementException("bad request");
            }
            updateChatRoomForSelect(chatRoom);
        }

        return chatMsgList;
    }
}
