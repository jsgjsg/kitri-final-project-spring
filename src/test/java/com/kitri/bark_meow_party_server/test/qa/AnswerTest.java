package com.kitri.bark_meow_party_server.test.qa;

import com.kitri.bark_meow_party_server.controller.AnswerController;
import com.kitri.bark_meow_party_server.domain.Answer;
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
public class AnswerTest {
    @Autowired
    AnswerController answerController;

    @Test
    public void test() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "teest", "teest", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "teest", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Answer answer = new Answer();
        answer.setAnswer("This is a test");
        Long qaId = 25L;
        answerController.createAnswer(qaId, answer);
        List<Answer> answers = answerController.getByQaId(qaId);
        assertThat(answers).isNotNull();
    }
    @Test
    public void createAnswer() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "teest", "teest", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "teest", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Answer answer = new Answer();
        answer.setAnswer("This is a test");
        Long qaId = 25L;
        answerController.createAnswer(qaId, answer);
        assertThat(answerController.createAnswer(qaId, answer)).isNotNull();
    }
    @Test
    void updateAnswer() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "teest", "teest", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "teest", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Answer answer = new Answer();
        answer.setAnswer("This is a test");
        Long qaId = 25L;
        Long answerId = 5L;
        answerController.createAnswer(qaId, answer);
        answerController.updateAnswer(qaId, answerId, answer);
        List<Answer> answers = answerController.getByQaId(qaId);
        assertThat(answers).isNotNull();
    }
    @Test
    void deleteAnswer() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "teest", "teest", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "teest", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Answer answer = new Answer();
        answer.setAnswer("This is a test");
        Long qaId = 25L;
        answerController.createAnswer(qaId, answer);
        answerController.deleteAnswer(qaId, answer.getId());
        List<Answer> answers = answerController.getByQaId(qaId);
        assertThat(answers).isNotNull();
    }
}
