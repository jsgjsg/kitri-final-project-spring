package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.dto.QaQuestionDTO;
import com.kitri.bark_meow_party_server.service.QAService;
import com.kitri.bark_meow_party_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QAController {
    @Autowired
    private QAService qaService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/qa")
    public List<QA> getQA() {
        return qaService.getQA();
    }

    @GetMapping("/qa/{userId}")
    public List<QA> getQuestion(@PathVariable Long userId) {
        return qaService.getQAByuserId(userId);
    }

    @GetMapping("/qa/search")
    public List<QA> searchQA(@RequestParam String query) {
        return qaService.searchQA(query);
    }

    @PostMapping("/qa")
    public void createQA(@RequestBody QaQuestionDTO qaQuestion) {
        qaService.insertQA(qaQuestion);
    }

    @DeleteMapping("/qa/{id}")
    public void deleteQA(@PathVariable Long id) {
        qaService.deleteQAById(id);
    }
}

