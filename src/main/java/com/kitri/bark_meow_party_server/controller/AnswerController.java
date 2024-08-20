package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Answer;
import com.kitri.bark_meow_party_server.dto.AnswerWithUserDTO;
import com.kitri.bark_meow_party_server.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @PostMapping("/{qaId}/answers/create")
    public Answer createAnswer(@PathVariable Long qaId, @RequestBody Answer answer) {
        answer.setQaId(qaId);
        answerService.addAnswer(answer);
        return answer;
    }
    @GetMapping("/{qaId}/answers")
    public List<AnswerWithUserDTO> getByQaId(@PathVariable Long qaId) {
        return answerService.getByQaId(qaId);
    }
    @GetMapping("/{qaId}/answers/{userId}")
    public List<Answer> getAnswer(@PathVariable Long qaId, @PathVariable Long userId) {
        return answerService.getAnswerByDoctorId(userId);
    }
    @PutMapping("/{qaId}/answers/{answerId}/update")
    public void updateAnswer(@PathVariable Long qaId, @PathVariable Long answerId, @RequestBody Answer answer) {
        answer.setId(answerId);
        answer.setQaId(qaId);
        answerService.updateAnswer(answer);
    }
    @DeleteMapping("/{qaId}/answers/{answerId}/delete")
    public void deleteAnswer(@PathVariable Long qaId, @PathVariable Long answerId) {
        answerService.deleteAnswer(answerId);
    }

}
