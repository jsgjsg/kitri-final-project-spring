package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor {
    private Long id;
    private String nickname;
    private String doctorname;
    private String password;
    private String image;
    private String hospital;
}
