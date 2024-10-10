package com.kb.chat.controller;

import com.kb.chat.dto.ChatMsg;
import com.kb.chat.dto.ChatRoom;
import com.kb.chat.service.ChatService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
@Api(value = "ChatController", tags = "채팅 정보")
public class ChatController {
    private final ChatService service;

    @GetMapping("/roomList")
    public ResponseEntity<List<ChatRoom>> getAllRooms(@RequestParam long uno) {
        return ResponseEntity.ok(service.getChatRooms(uno));
    }

    @PostMapping("room")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom) {
        return ResponseEntity.ok(service.createChatRoom(chatRoom));
    }

    @GetMapping("/room/{cno}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable("cno") long cno) {
        return ResponseEntity.ok(service.getChatRoomByCno(cno));
    }

    @PostMapping("/send")
    public ResponseEntity<ChatRoom> sendMessage(@RequestBody ChatMsg chatMsg) {
        return ResponseEntity.ok(service.sendMsg(chatMsg));
    }

    @GetMapping("")
    public ResponseEntity<List<ChatMsg>> getChatMsgListById(@RequestParam("room") long roomId, @RequestParam("uno") long uno) {
        List<ChatMsg> list = service.getAllChatMsg(roomId, uno);
        return ResponseEntity.ok(list);
    }
}