package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.QaQuestionDTO;
import com.kitri.bark_meow_party_server.mapper.QAMapper;
import com.kitri.bark_meow_party_server.mapper.QuestionMapper;
import com.kitri.bark_meow_party_server.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QAService {
    @Autowired
    QAMapper qaMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @Autowired
    SearchMapper searchMapper;

    public List<QA> getQA() {
        return qaMapper.selectAll();
    }
    public QA getQAById(Long id) {
        return qaMapper.selectById(id);
    }
    public List<QA> getQAByuserId(Long userId){
        return qaMapper.selectByUserId(userId);
    }
    public List<QA> searchQA(String query) {
        return searchMapper.searchByQaQuery(query);
    }

    @Transactional
    public Long insertQA(QaQuestionDTO qaQuestion) {
        QA qa = qaQuestion.getQa();
        Question question = qaQuestion.getQuestion();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        qa.setUserId(user.getId());

        Long qaId = qaMapper.QaInsert(qa);
        question.setQaId(qa.getId());

        questionService.addQuestion(question);

        return qaId;
    }

    @Transactional
    public void updateQA(QA qa, Question question) {
        qaMapper.QaUpdate(qa);
        questionMapper.questionUpdate(question);
    }

    @Transactional
    public void deleteQAById(Long id) {
        questionMapper.questionDelete(id);
        qaMapper.QaDelete(id);
    }
}
