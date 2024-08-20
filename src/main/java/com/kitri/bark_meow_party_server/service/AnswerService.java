package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Answer;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.AnswerWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;

    public List<Answer> getAnswers() {
        answerMapper.getAll();
        return answerMapper.getAll();
    }
    public List<Answer> getAnswerByDoctorId(Long doctorId) {
        return answerMapper.getByDoctorId(doctorId);
    }
    public Answer getAnswerById(Long id) {
        return answerMapper.getById(id);
    }
    public List<AnswerWithUserDTO> getByQaId (Long qaId) {
        return answerMapper.getByQaId(qaId);
    }
    public Answer addAnswer(Answer answer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = doctorService.findByDoctorname(userName);
        answer.setUserId(user.getId());
        //user로 테스트 시 밑 주석 해제하고, 윗부분 String doctorName부터 주석처리
//        String username = authentication.getName();
//        User user = userService.findByUsername(username);
//        answer.setUserId(user.getId());

        answerMapper.createAnswer(answer);
        return answer;
    }
    public void updateAnswer(Answer answer) {
        answerMapper.updateAnswer(answer);
    }
    public void deleteAnswer(Long id) {
        answerMapper.deleteAnswer(id);
    }
}
