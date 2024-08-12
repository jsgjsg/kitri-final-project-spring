package com.kitri.bark_meow_party_server.test.qa;

import com.kitri.bark_meow_party_server.controller.QuestionController;
import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.dto.QuestionWithUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class QuestionTest {
    @Autowired
    QuestionController questionController;
    @Test
    public void test() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Question question = new Question();
        question.setQuestion("This is a question");
        Long qaId = 25L;
        questionController.createQuestion(qaId, question);
        List<QuestionWithUserDTO> questions = questionController.getAllQuestions(qaId);
        assertThat(questions).isNotNull();
    }
    @Test
    public void createQuestion() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Question question = new Question();
        question.setQuestion("This is a question");
        Long qaId = 25L;
        questionController.createQuestion(qaId, question);
        assertThat(questionController.createQuestion(qaId, question)).isNotNull();
    }
    @Test
    void updateQuestion() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Question question = new Question();
        question.setQuestion("This is a question");
        Long qaId = 25L;
        Long questionId = 23L;
        questionController.createQuestion(qaId, question);
        question.setQuestion("This is a new question");
        questionController.updateQuestion(qaId, questionId, question);
        List<QuestionWithUserDTO> questions = questionController.getAllQuestions(qaId);
        assertThat(questions).isNotNull();
    }
    @Test
    void deleteQuestion() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Question question = new Question();
        question.setQuestion("This is a question");
        Long qaId = 25L;
        questionController.createQuestion(qaId, question);
        questionController.deleteQuestion(qaId, question.getId());
        List<QuestionWithUserDTO> questions = questionController.getAllQuestions(qaId);
        assertThat(questions).isNotNull();
    }
}
