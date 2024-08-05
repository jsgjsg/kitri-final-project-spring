package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Doctor;
import com.kitri.bark_meow_party_server.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
//    public User findByUsername(String username) {
//        return userMapper.findByUsername(username);
//    }
    @Autowired
    DoctorMapper doctorMapper;

    public Doctor findByDoctorname(String doctorname) {
        return doctorMapper.findByDoctorname(doctorname);
    }
}
