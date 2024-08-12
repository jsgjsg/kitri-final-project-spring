package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Inquiry {
    private Long id;
    private Long userId;
    private String image;
    private String title;
    private String inquiry;
    private LocalDateTime createdAt;
}
