package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public void signup(User user) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(true);

        // 사용자 저장
        userMapper.insert(user);

        // 권한 저장
        userMapper.insertAuthority(user.getId(), "ROLE_USER");
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public User findByNickname(String nickname) {
        return userMapper.findByNickname(nickname);
    }

    public boolean checkUsername(String username) {
        List<String> allUsernames = userMapper.findAllUsernames();

        return allUsernames.contains(username);
    }

    public boolean checkNickname(String nickname) {
        List<String> allUsernames = userMapper.findAllNicknames();

        return allUsernames.contains(nickname);
    }

    public void deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = findByUsername(username);

        userMapper.deleteById(user.getId());
    }
}
