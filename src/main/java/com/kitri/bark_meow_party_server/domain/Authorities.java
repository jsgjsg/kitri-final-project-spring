package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authorities {
    private Long id;
    private Long userId;
    private String authority;
}