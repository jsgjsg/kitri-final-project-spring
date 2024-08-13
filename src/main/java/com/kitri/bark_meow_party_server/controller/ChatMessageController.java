package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import com.kitri.bark_meow_party_server.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("")
    public ResponseEntity<?> getMessages() {
        try {
            List<ChatMessage> messages = chatMessageService.getMessages();
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> addMessage(@RequestBody TextMessage textMessage) {
        try {
            chatMessageService.insertMessage(textMessage);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
