package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.mapper.QAMapper;
import com.kitri.bark_meow_party_server.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QAService {
    @Autowired
    QAMapper qaMapper;
    @Autowired
    QuestionMapper questionMapper;

    public List<QA> getQA() {
        return qaMapper.selectAll();
    }
    public QA getQAById(Long id) {
        return qaMapper.selectById(id);
    }
}
