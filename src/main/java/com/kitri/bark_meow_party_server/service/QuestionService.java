package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.QAMapper;
import com.kitri.bark_meow_party_server.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserService userService;

    public List<Question> getQuestions() {
        return questionMapper.selectAll();
    }
    public Question getQuestionByUserId(Long userId) {
        return questionMapper.selectByUserId(userId);
    }
    public Question getQuestionById(Long id) {
        return questionMapper.selectById(id);
    }
    public List<Question> getQuestionByQaId(Long qaId) {
        return questionMapper.selectByQaId(qaId);
    }

    public void addQuestion(Question question) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        question.setUserId(user.getId());

        System.out.println("Adding question: " + question);
        questionMapper.questionInsert(question);
    }

    public void updateQuestion(Question question) {
        questionMapper.questionUpdate(question);
    }

    public void deleteQuestion(Long id) {
        questionMapper.questionDelete(id);
    }
}
