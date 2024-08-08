package com.kitri.bark_meow_party_server.test.qa;

import com.kitri.bark_meow_party_server.controller.AnswerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AnswerTest {
    @Autowired
    AnswerController answerController;
}
