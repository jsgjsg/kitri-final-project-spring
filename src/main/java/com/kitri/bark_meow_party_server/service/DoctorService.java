package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DoctorMapper doctorMapper;

    public User findByDoctorname(String username) {
        return doctorMapper.findByDoctorname(username);
    }

    public void signup(User user) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(true);

        // 사용자 저장
        doctorMapper.insert(user);

        // 권한 저장
        doctorMapper.insertAuthority(user.getId(), "ROLE_DOCTOR");
    }

    public boolean checkDoctorname(String username) {
        List<String> allDoctorname = doctorMapper.getAllDoctorname();
        return allDoctorname.contains(username);
    }
    public boolean checkNickname(String nickname) {
        List<String> allDoctorname = doctorMapper.getAllNickname();
        return allDoctorname.contains(nickname);
    }
}
