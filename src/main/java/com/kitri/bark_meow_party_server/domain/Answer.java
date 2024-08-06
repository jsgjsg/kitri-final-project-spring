package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Answer {
    private Long id;
    private Long doctorId;
    private Long userId;
    private Long qaId;
    private String answer;
    private LocalDateTime createdAt;
}
