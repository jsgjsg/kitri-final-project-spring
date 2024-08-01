package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentWithUserDTO extends ProfileResponseDTO {
    private long id;
    private long reviewId;
    private String content;
    private long userId;
    private LocalDateTime createdAt;

}
