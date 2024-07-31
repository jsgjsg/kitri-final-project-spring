package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.mapper.FeedCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedCommentService {
    @Autowired
    FeedCommentMapper feedCommentMapper;
    public List<FeedComment> getFeedComments() {
        return feedCommentMapper.selectAll();
    }
    public FeedComment getFeedComment(Long id) {
        return feedCommentMapper.selectById(id);
    }
    public void addReviewComment(FeedComment feedComment) {
        feedComment.setCreatedAt(LocalDateTime.now());
        feedCommentMapper.feedCommentInsert(feedComment);
    }
    public void updateReviewComment(FeedComment feedComment) {
        feedCommentMapper.feedCommentUpdate(feedComment);
    }
    public void deleteReviewComment(Long id) {
        feedCommentMapper.deleteFeedComment(id);
    }
}
