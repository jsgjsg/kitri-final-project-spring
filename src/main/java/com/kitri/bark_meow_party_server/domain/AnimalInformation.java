package com.kitri.bark_meow_party_server.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalInformation {
    private Long id;
    private Long userId;
    private String name;
    private String image;
    private String type;
    private String year;
    private String month;
    private String day;
    private String gender;
    private String neutralization; //중성화
    private String weight;
}
