package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Inquiry;
import com.kitri.bark_meow_party_server.service.InquiryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {
    @Autowired
    InquiryService inquiryService;

    @GetMapping("")
    public List<Inquiry> getInquiry() {
        return inquiryService.getAllInquiry();
    }

    @GetMapping("/{id}")
    public Inquiry getInquiryById(@PathVariable Long id) {
        return inquiryService.getInquiryById(id);
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
