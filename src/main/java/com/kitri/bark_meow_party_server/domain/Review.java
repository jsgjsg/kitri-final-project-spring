package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Review {
    private Long id;
    private String item;
    private String good;
    private String bad;
    private String tip;
    private String image;
    private String repurchase;
    private Double satisfaction;
    private String animal;
    private String category;
    private Long userId;
    private LocalDateTime createdAt;
}
