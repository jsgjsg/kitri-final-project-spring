package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Question {
    private Long id;
    private Long userId;
    private Long qaId;
    private String question;
    private LocalDateTime createdAt;
}
