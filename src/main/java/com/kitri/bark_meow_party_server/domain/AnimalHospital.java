package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalHospital {
    private String fcltyFlagNm;
    private String fcltyNm;
    private String rdnmadrNm;
    private String ctprvnKlangNm;
    private String signguKlangNm;
    private String ctprvnEngNm;
    private String signguEngNm;
    private String ctprvnCd;
    private String signguCd;
    private String fcltyLa;
    private String fcltyLo;
    private String telNo;
    private String baseDe;
}