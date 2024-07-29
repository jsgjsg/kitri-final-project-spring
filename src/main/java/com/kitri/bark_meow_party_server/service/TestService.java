package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Test;
import com.kitri.bark_meow_party_server.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> getTests() {
        return testMapper.findAll();
    }
}
