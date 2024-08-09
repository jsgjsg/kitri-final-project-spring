package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.mapper.FriendsMapper;
import com.kitri.bark_meow_party_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {
    @Autowired
    UserService userService;
    @Autowired
    FriendsMapper friendsMapper;
    @Autowired
    private UserMapper userMapper;

    // 친구 목록 조회
    public List<ProfileResponseDTO> getFriends() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        return friendsMapper.findAllFriends(user.getId());
    }

    // 친구 삭제
    public void deleteFriend(long friendId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        long userId = user.getId();

        // 친구 관계는 id 순
        if(userId < friendId) friendsMapper.deleteFriend(userId, friendId);
        else friendsMapper.deleteFriend(friendId, userId);
    }

    // 친구 요청 보내기
    public void requestFriend(String friendNickname) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User requester = userService.findByUsername(username);
        User receiver = userService.findByNickname(friendNickname);

        friendsMapper.requestFriend(requester.getId(), receiver.getId());
    }

    // 보낸 요청 조회
    public List<ProfileResponseDTO> getRequestFriend() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        return friendsMapper.getRequestFriend(user.getId());
    }

    // 보낸 요청 취소
    public void deleteRequestFriend(long requestId) {
        friendsMapper.deleteRequestFriend(requestId);
    }

    public List<ProfileResponseDTO> getReceivedFriend() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        return friendsMapper.getReceivedFriend(user.getId());
    }

}
