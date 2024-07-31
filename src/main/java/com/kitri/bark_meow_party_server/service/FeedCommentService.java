package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.mapper.FeedCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedCommentService {
    //FeedCommentMapper를 통해 데이터베이스 작업을 수행
    @Autowired //FeedCommentMapper 자동 의존성 주입
    FeedCommentMapper feedCommentMapper;
    //모든 피드 댓글을 조회
    public List<FeedComment> getFeedComments() {
        return feedCommentMapper.selectAll();
    }
    //주어진 ID에 해당하는 피드 댓글을 조회
    public FeedComment getFeedComment(Long id) {
        return feedCommentMapper.selectById(id);
    }
    //새 피드 댓글을 데이터베이스에 추가
    public void addFeedComment(FeedComment feedComment) {
        feedComment.setCreatedAt(LocalDateTime.now());
        feedCommentMapper.feedCommentInsert(feedComment);
    }
    //주어진 피드 댓글 객체의 정보를 수정
    public void updateFeedComment(FeedComment feedComment) {
        feedCommentMapper.feedCommentUpdate(feedComment);
    }
    //주어진 ID에 해당하는 피드 댓글을 삭제
    public void deleteFeedComment(Long id) {
        feedCommentMapper.deleteFeedComment(id);
    }
}
