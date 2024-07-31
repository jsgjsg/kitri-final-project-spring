package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String nickname;
    private String username;
    private String password;
    private String location;
    private String introduce;
    private String image;
    private Boolean enabled;
}