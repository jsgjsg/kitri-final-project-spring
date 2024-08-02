package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.mapper.QAMapper;
import com.kitri.bark_meow_party_server.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public List<Question> getQuestions() {
        return questionMapper.selectAll();
    }
    public Question getQuestionByUserId(Long userId) {
        return questionMapper.selectByUserId(userId);
    }
    public Question getQuestionById(Long id) {
        return questionMapper.selectById(id);
    }

    public void addQuestion(Question question) {
        questionMapper.questionInsert(question);
    }

    public void updateQuestion(Question question) {
        questionMapper.questionUpdate(question);
    }

    public void deleteQuestion(Long id) {
        questionMapper.questionDelete(id);
    }
}
