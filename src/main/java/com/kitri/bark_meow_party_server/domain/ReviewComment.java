package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewComment {
    private Long id;
    private Long reviewId;
    private String content;
    private LocalDateTime createdAt;
}
