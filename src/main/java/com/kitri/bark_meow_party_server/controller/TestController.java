package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Test;
import com.kitri.bark_meow_party_server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    public List<Test> test() {


        return testService.getTests();
    }
}
