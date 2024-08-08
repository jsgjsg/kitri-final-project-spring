package com.kitri.bark_meow_party_server.test.qa;

import com.kitri.bark_meow_party_server.controller.QAController;
import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.dto.QaQuestionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@SpringBootTest
@Transactional
public class QATest {
    @Autowired
    QAController qaController;
    @Test
    public void testQA() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        qaController.getQA();
    }
    @Test
    void createdAQ() throws Exception {
        // 인증 객체를 설정
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // QA 객체 생성 및 설정
        QA qa = new QA();
        qa.setTitle("title");

        // Question 객체 생성 및 설정
        Question question = new Question();
        question.setQuestion("question");

        // QaQuestionDTO 객체 생성 및 QA, Question 설정
        QaQuestionDTO qaQuestionDTO = new QaQuestionDTO();
        qaQuestionDTO.setQa(qa);
        qaQuestionDTO.setQuestion(question);

        // 컨트롤러 메서드 호출
        qaController.createQA(qaQuestionDTO);

    }

}
