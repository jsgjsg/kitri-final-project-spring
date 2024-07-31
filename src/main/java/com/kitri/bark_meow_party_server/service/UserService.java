package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void save(User user) {
//        // 비밀번호 암호화
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);

        // 사용자 저장
        userMapper.insertUser(user);

        // 권한 저장
        userMapper.insertAuthority(user.getId(), "ROLE_USER");
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
