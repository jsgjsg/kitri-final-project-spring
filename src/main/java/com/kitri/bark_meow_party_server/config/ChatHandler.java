package com.kitri.bark_meow_party_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    // Jackson ObjectMapper 인스턴스 생성 (JSON 파싱에 사용)
    private final ObjectMapper objectMapper = new ObjectMapper();

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

        // 메시지를 JSON 형식으로 파싱
        String payload = message.getPayload();
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        // 채팅 메시지에 필요한 정보 추가
        chatMessage.setRoomId(roomId);
        chatMessage.setTimestamp(LocalDateTime.now()); // 타임스탬프 설정

        // 데이터베이스에 메시지 저장
        chatMessageMapper.insertMessage(chatMessage);

        // 모든 클라이언트에게 메시지 전송
        for (WebSocketSession webSocketSession : roomSessions.get(roomId)) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatMessage)));
            }
        }
    }

    private String getRoomId(WebSocketSession session) {
        // 세션 URL에서 roomId 추출 (예: /chat/roomId)
        String path = session.getUri().getPath();
        return path.split("/")[2];
    }
}
