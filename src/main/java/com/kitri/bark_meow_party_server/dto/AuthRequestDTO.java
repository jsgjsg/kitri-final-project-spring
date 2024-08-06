package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthRequestDTO {
    private String username;
    private String password;
}
