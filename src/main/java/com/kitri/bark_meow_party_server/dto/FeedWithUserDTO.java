package com.kitri.bark_meow_party_server.dto;

import com.kitri.bark_meow_party_server.domain.Feed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedWithUserDTO extends ProfileResponseDTO {
    private long id;
    private long userId;
    private String image;
    private String content;
    private String animal;
    private LocalDateTime createdAt;
}