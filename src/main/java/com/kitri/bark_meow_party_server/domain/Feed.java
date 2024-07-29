package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    private long id;
    private long user_id;
    private String image;
    private String content;
    private String animal;
    private LocalDateTime createdAt;
}
