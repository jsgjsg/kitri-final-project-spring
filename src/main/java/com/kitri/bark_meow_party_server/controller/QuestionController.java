package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.dto.QuestionWithUserDTO;
import com.kitri.bark_meow_party_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    //질문작성
    @PostMapping("/{qaId}/questions")
    public Question createQuestion(@PathVariable Long qaId, @RequestBody Question question) {
        question.setQaId(qaId);
        System.out.println("Created question: " + question);
        questionService.addQuestion(question);
        return question;
    }
    //질문 조회
    @GetMapping("/{qaId}/questions")
    public List<QuestionWithUserDTO> getAllQuestions(@PathVariable Long qaId) {
        return questionService.getQuestionByQaId(qaId);
    }
    //유저별 질문조회
    @GetMapping("/{qaId}/questions/{userId}")
    public List<Question> getQuestion(@PathVariable Long qaId, @PathVariable Long userId) {
        return questionService.getQuestionByUserId(userId);
    }
    //질문수정
    @PutMapping("/{qaId}/questions/{questionId}")
    public void updateQuestion(@PathVariable Long qaId, @PathVariable Long questionId, @RequestBody Question question) {
        question.setId(questionId);
        question.setQaId(qaId);
        questionService.updateQuestion(question);
    }
    //질문삭제
    @DeleteMapping("/{qaId}/questions/{questionId}")
    public void deleteQuestion(@PathVariable Long qaId, @PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }
}
