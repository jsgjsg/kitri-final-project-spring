package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Inquiry;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.InquiryWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {
    @Autowired
    InquiryMapper inquiryMapper;
    @Autowired
    UserService userService;

    public List<InquiryWithUserDTO> getAllInquiry() {
        return inquiryMapper.getInquiryAll();
    }
    //페이징
    public List<Inquiry> getInquiry(int page, int size) {
        int offset = (page - 1) * size;
        return inquiryMapper.selectByOffsetAndLimit(offset, size);
    }
    public int getInquiryCount() {
        return inquiryMapper.count();
    }

    public Inquiry getInquiryById(Long id) {
        return inquiryMapper.getByInquiryId(id);
    }

    public void addInquiry(Inquiry inquiry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        inquiry.setUserId(user.getId());

        inquiryMapper.insertInquiry(inquiry);
    }

    public void updateInquiry(Inquiry inquiry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        inquiry.setUserId(user.getId());

        inquiryMapper.updateInquiry(inquiry);
    }

    public void deleteInquiryById(Long id) {
        inquiryMapper.deleteInquiry(id);
    }
}
