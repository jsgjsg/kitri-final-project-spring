package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.ChatMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    public List<ChatMessage> getMessages() {
        return chatMessageMapper.findAll();
    }

    public void insertMessage(TextMessage textMessage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(username);
        chatMessage.setMessage(String.valueOf(textMessage));

        chatMessageMapper.insertMessage(chatMessage);
    }
}
