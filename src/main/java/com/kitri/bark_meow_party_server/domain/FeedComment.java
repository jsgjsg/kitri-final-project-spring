package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FeedComment {
    private Long id;
    private Long feedId;
    private String content;
    private LocalDateTime createdAt;
}
