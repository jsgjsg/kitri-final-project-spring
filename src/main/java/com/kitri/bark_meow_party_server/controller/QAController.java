package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.service.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QAController {
    @Autowired
    private QAService qaService;

//    @GetMapping("/reviews")
//    public List<Review> getReviews() {
//        //reviewService에서 작성 한 후기 조회 로직을 가져온다.
//        reviewService.findAll();
//        return reviewService.findAll();
//    }

    @GetMapping("/qa")
    public List<QA> getQA() {
        return qaService.getQA();
    }
}
