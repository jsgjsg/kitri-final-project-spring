package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private long id;
    private String roomId;
    private String message;
    private String sender;
    private LocalDateTime timestamp;
}
