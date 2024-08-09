package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {

    @Autowired
    UserService userService;
    @Autowired
    private UserMapper userMapper;

    public ProfileResponseDTO getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return new ProfileResponseDTO(userService.findByUsername(username));
    }

    public void updateMyProfile(ProfileResponseDTO profileResponseDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        profileResponseDTO.setId(user.getId());

        userMapper.update(profileResponseDTO);
    }

    public void updateMyPassword(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User tmpUser = userService.findByUsername(username);
        user.setId(tmpUser.getId());

        userMapper.updatePassword(user);
    }

}
