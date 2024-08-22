package com.kitri.bark_meow_party_server.config;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import com.kitri.bark_meow_party_server.mapper.ChatMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatHandler extends TextWebSocketHandler {
    @Autowired
    ChatMessageMapper chatMessageMapper;

    // 채팅방 ID와 WebSocket 세션을 매핑
    private final ConcurrentHashMap<String, Set<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = getRoomId(session);
        roomSessions.computeIfAbsent(roomId, k -> Collections.synchronizedSet(new CopyOnWriteArraySet<>()));
        roomSessions.get(roomId).add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String roomId = getRoomId(session);
        roomSessions.get(roomId).remove(session);
        if (roomSessions.get(roomId).isEmpty()) {
            roomSessions.remove(roomId);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String roomId = getRoomId(session);

        // 메시지를 저장할 때 필요한 정보 추출
        String payload = message.getPayload();  // 메시지 내용 (형식: "sender: message")
        String[] splitPayload = payload.split(": ", 2);
        String sender = splitPayload[0];
        String content = splitPayload[1];

        // 채팅 메시지를 데이터베이스에 저장
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRoomId(roomId);
        chatMessage.setSender(sender);
        chatMessage.setMessage(content);
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessageMapper.insertMessage(chatMessage);

        // 모든 클라이언트에게 메시지 전송
        for (WebSocketSession webSocketSession : roomSessions.get(roomId)) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    private String getRoomId(WebSocketSession session) {
        // 세션 URL에서 roomId 추출 (예: /chat/roomId)
        String path = session.getUri().getPath();
        return path.split("/")[2];
    }
}
