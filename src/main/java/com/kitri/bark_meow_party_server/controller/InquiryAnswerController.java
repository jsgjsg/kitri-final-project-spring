package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.InquiryAnswer;
import com.kitri.bark_meow_party_server.service.InquiryAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiry/answer")
public class InquiryAnswerController {
    @Autowired
    InquiryAnswerService inquiryAnswerService;

    @GetMapping("/list")
    public List<InquiryAnswer> getAnswers() {
        return inquiryAnswerService.getInquiryAnswers();
    }
    @GetMapping("/{inquiryId}")
    public List<InquiryAnswer> getAnswer(@PathVariable Long inquiryId) {
        return inquiryAnswerService.getInquiryAnswerByInquiryId(inquiryId);
    }

    @PostMapping("/{inquiryId}/create")
    public InquiryAnswer addAnswer(@PathVariable Long inquiryId, @RequestBody InquiryAnswer answer) {
        inquiryAnswerService.insertInquiryAnswer(inquiryId, answer);
        return answer;
    }

    @PutMapping("/{id}/update")
    public InquiryAnswer updateInquiryAnswer(@PathVariable Long id, @RequestBody InquiryAnswer answer) {
        answer.setId(id);
        inquiryAnswerService.updateInquiryAnswer(answer);
        return answer;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAnswer(@PathVariable Long id) {
        inquiryAnswerService.deleteInquiryAnswer(id);
    }
}
