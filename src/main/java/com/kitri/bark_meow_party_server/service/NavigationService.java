package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.AnimalHospital;
import com.kitri.bark_meow_party_server.mapper.NavigationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationService {

    @Autowired
    NavigationMapper navigationMapper;

    public List<AnimalHospital> findHospital(String city) {
        if (city == null || city.isEmpty()) {
            return navigationMapper.findAllHospital();
        }
        return navigationMapper.findHospitalInCity(city);
    }
}
