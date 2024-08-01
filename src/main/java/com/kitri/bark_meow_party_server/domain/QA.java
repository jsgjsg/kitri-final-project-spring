package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QA {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
