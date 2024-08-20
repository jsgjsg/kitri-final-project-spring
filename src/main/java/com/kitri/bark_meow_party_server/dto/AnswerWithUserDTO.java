package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerWithUserDTO extends ProfileResponseDTO {
    private long id;
    private long userId;
    private Long qaId;
    private String answer;
    private LocalDateTime createdAt;
}
