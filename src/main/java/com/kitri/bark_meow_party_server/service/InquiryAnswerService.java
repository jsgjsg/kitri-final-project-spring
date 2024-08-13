package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.InquiryAnswer;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.InquiryAnswerMapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryAnswerService {
    @Autowired
    private InquiryAnswerMapper inquiryAnswerMapper;
    @Autowired
    private UserService userService;

    public List<InquiryAnswer> getInquiryAnswers() {
        return inquiryAnswerMapper.getInquiryAnswers();
    }
    public InquiryAnswer getInquiryAnswerById(Long id) {
        return inquiryAnswerMapper.getInquiryAnswerById(id);
    }
    public List<InquiryAnswer> getInquiryAnswerByInquiryId(Long inquiryId) {
        return inquiryAnswerMapper.getInquiryIdAnswer(inquiryId);
    }

    public void insertInquiryAnswer(Long inquiryId, InquiryAnswer answer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        answer.setUserId(user.getId());
        answer.setInquiryId(inquiryId);
        answer.setCreatedAt(LocalDateTime.now());
        inquiryAnswerMapper.addInquiryAnswer(answer);
    }

    public void updateInquiryAnswer(InquiryAnswer answer) {
        inquiryAnswerMapper.updateInquiryAnswer(answer);
    }

    public void deleteInquiryAnswer(Long id) {
        inquiryAnswerMapper.deleteInquiryAnswer(id);
    }
}
