package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedCommentWithUserDTO extends ProfileResponseDTO {
    private long id;
    private long userId;
    private long feedId;
    private String content;
    private LocalDateTime createdAt;
}
