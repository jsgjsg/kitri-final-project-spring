package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.mapper.ReviewCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewCommentService {
    @Autowired
    ReviewCommentMapper reviewCommentMapper;
    public List<ReviewComment> getReviewComments() {
        return reviewCommentMapper.selectAll();
    }
    public ReviewComment getReviewComment(Long id) {
        return reviewCommentMapper.selectById(id);
    }
    public void addReviewComment(ReviewComment reviewComment) {
        reviewComment.setCreatedAt(LocalDateTime.now());
        reviewCommentMapper.reviewCommentInsert(reviewComment);
    }
    public void updateReviewComment(ReviewComment reviewComment) {
        reviewCommentMapper.reviewCommentUpdate(reviewComment);
    }
    public void deleteReviewComment(Long id) {
        reviewCommentMapper.deleteReviewComment(id);
    }
}
