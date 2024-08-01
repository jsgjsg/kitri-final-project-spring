package com.kitri.bark_meow_party_server.dto;

import com.kitri.bark_meow_party_server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private long id;
    private String nickname;
    private String location;
    private String introduce;
    private String image;

    // User 객체를 받아서 ProfileResponseDTO로 변환하는 생성자
    public ProfileResponseDTO(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.location = user.getLocation();
        this.introduce = user.getIntroduce();
        this.image = user.getImage();
    }

}
