package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("")
    public ResponseEntity<?> updateMyProfile(@RequestBody ProfileResponseDTO profileResponseDTO) {
        try {
            myPageService.updateMyProfile(profileResponseDTO);
            return ResponseEntity.ok("회원정보 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("change-password")
    public ResponseEntity<?> updateMyPassword(@RequestBody User user) {
        try {
            myPageService.updateMyPassword(user);
            return ResponseEntity.ok("비밀번호 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
