package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import com.kitri.bark_meow_party_server.dto.ChatRoomsDTO;
import com.kitri.bark_meow_party_server.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    // 이전 채팅 불러오기
    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(@PathVariable String roomId) {
        try {
            List<ChatMessage> messages = chatMessageService.getMessages(roomId);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms() {
        try {
            List<ChatRoomsDTO> rooms = chatMessageService.getRooms();
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping("")
//    public ResponseEntity<?> addMessage(@RequestBody TextMessage textMessage) {
//        try {
//            chatMessageService.insertMessage(textMessage);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
