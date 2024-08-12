package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryWithUserDTO {
    private Long id;
    private Long userId;
    private String image;
    private String title;
    private String inquiry;
    private LocalDateTime createdAt;
}
