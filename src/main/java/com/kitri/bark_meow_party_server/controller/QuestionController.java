package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qa")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

//    @PostMapping("/reviews/{reviewId}/comments")
//    public ReviewComment addReviewComment(@PathVariable Long reviewId, @RequestBody ReviewComment reviewComment) {
//        //reviewCommentService 작성 한 후기에 대한 댓글 작성 로직을 가져온다.
//        reviewCommentService.addReviewComment(reviewId,reviewComment);
//        return reviewComment;
//    }
    @PostMapping("/{qaId}/questions")
    public Question createQuestion(@PathVariable Long qaId, @RequestBody Question question) {
        questionService.addQuestion(question);
        return question;
    }
    @GetMapping("/{qaId}/questions")
    public List<Question> getQuestions(@PathVariable Long qaId) {
        return questionService.getQuestions();
    }
    @PutMapping("/{qaId}/questions/{questionId}")
    public void updateQuestion(@PathVariable Long qaId, @PathVariable Long questionId, @RequestBody Question question) {
        questionService.updateQuestion(question);
    }
    @DeleteMapping("/{qaId}/questions/{questionId}")
    public void deleteQuestion(@PathVariable Long qaId, @PathVariable Long questionId) {
        questionService.deleteQuestion(questionId);
    }
}
