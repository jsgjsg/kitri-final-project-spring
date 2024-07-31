package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/me")
public class MyPageController {

    @Autowired
    private MyPageService myPageService;

    @GetMapping("")
    public ResponseEntity<ProfileResponseDTO> getMyProfile() {
        ProfileResponseDTO myProfile = myPageService.getMyProfile();
        return ResponseEntity.ok(myProfile);
    }
}
