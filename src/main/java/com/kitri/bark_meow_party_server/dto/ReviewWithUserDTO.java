package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewWithUserDTO extends ProfileResponseDTO {
    private long id;
    private long userId;
    private String item;
    private String good;
    private String bad;
    private String tip;
    private String image;
    private String repurchase;
    private Double satisfaction;
    private String animal;
    private String category;
    private LocalDateTime createdAt;
}
