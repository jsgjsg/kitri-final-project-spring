package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryAnswer {
    private Long id;
    private Long userId;
    private Long inquiryId;
    private String inquiryAnswer;
    private LocalDateTime createdAt;
}
