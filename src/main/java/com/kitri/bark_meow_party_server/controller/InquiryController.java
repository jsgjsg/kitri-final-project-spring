package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Inquiry;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.InquiryWithUserDTO;
import com.kitri.bark_meow_party_server.service.InquiryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {
    @Autowired
    InquiryService inquiryService;

    @GetMapping("")
    public List<InquiryWithUserDTO> getInquiry() {
        return inquiryService.getAllInquiry();
    }

    @GetMapping("/{id}")
    public Inquiry getInquiryById(@PathVariable Long id) {
        return inquiryService.getInquiryById(id);
    }

    @GetMapping("/my/{userId}")
    public List<Inquiry> getMyInquiry(@PathVariable Long userId) {
        return inquiryService.getInquiryByUserId(userId);
    }

    //페이징
    @GetMapping("/page")
    public Map<String, Object> getInquiryPage(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Inquiry> inquiry = inquiryService.getInquiry(page, size);
        int total = inquiryService.getInquiryCount();
        Map<String, Object> result = new HashMap<>();
        result.put("inquiry", inquiry);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    @PostMapping("")
    public Inquiry createInquiry(@RequestBody Inquiry inquiry) {
        inquiryService.addInquiry(inquiry);
        return inquiry;
    }

    @PutMapping("/{inquiryId}/update")
    public Inquiry updateInquiry(@PathVariable Long inquiryId, @RequestBody Inquiry inquiry) {
        inquiry.setId(inquiryId);
        inquiryService.updateInquiry(inquiry);
        return inquiry;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiryById(id);
    }
}
