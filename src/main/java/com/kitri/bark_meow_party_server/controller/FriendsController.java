package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {
    @Autowired
    private FriendsService friendsService;

    @GetMapping("") // 친구 목록 조회
    public ResponseEntity<?> getFriends() {
        try {
            List<ProfileResponseDTO> friends = friendsService.getFriends();
            return ResponseEntity.ok(friends);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{friendId}/delete") // 친구 삭제
    public ResponseEntity<?> deleteFriend(@PathVariable("friendId") long friendId) {
        try {
            friendsService.deleteFriend(friendId);
            return ResponseEntity.ok("Friend deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/requests") // 친구 요청 보내기
    public ResponseEntity<?> requestFriend(@RequestBody long friendId) {
        try {
            friendsService.requestFriend(friendId);
            return ResponseEntity.ok("Friend request accepted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/requests/sent") // 보낸 요청 조회
    public ResponseEntity<?> getRequestFriend() {
        try {
            List<ProfileResponseDTO> requestFriend = friendsService.getRequestFriend();
            return ResponseEntity.ok(requestFriend);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/requests/sent/{requestId}") // 보낸 요청 취소
    public ResponseEntity<?> deleteRequestFriend(@PathVariable long requestId) {
        try {
            friendsService.deleteRequestFriend(requestId);
            return ResponseEntity.ok("delete request accepted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/requests/received") // 받은 요청 조회
    public ResponseEntity<?> getReceivedFriend() {
        try {
            List<ProfileResponseDTO> receivedFriend = friendsService.getReceivedFriend();
            return ResponseEntity.ok(receivedFriend);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/requests/received/{requestId}/accept") // 받은 요청 수락
    public ResponseEntity<?> acceptFriend(@PathVariable long requestId) {
        try {
            friendsService.acceptFriend(requestId);
            return ResponseEntity.ok("accept request accepted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/requests/received/{requestId}/reject") // 받은 요청 거절
    public ResponseEntity<?> rejectFriend(@PathVariable long requestId) {
        try {
            friendsService.rejectFriend(requestId);
            return ResponseEntity.ok("reject request accepted");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/search-nickname") // 닉네임으로 검색
    public ResponseEntity<?> searchNickname(@RequestBody String nickname) {
        try {
            nickname = nickname.substring(1, nickname.length() - 1);
            ProfileResponseDTO profileResponseDTO = friendsService.searchNickname(nickname);
            return ResponseEntity.ok(profileResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}