package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.ReviewCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewCommentService {
    @Autowired //ReviewCommentMapper 자동 의존성 주입
    ReviewCommentMapper reviewCommentMapper;
    @Autowired //UserService 자동 의존성 주입
    UserService userService;

    //후기 댓글 조회
    public List<ReviewComment> getReviewComments() {
        return reviewCommentMapper.selectAll();
    }

//    public ReviewComment getReviewComment(Long id) {
//        return reviewCommentMapper.selectById(id);
//    }

    // 특정 후기 ID에 대한 댓글 조회
    public List<ReviewComment> getReviewCommentsByReviewId(Long reviewId) {
        return reviewCommentMapper.selectByReviewId(reviewId);
    }
    //후기에 대한 댓글 작성
    public void addReviewComment(Long reviewId, ReviewComment reviewComment) {
        //로그인 한 유저로 작성자를 고정 시키기 위한 로직
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails) principal).getUsername();
//
//        User user = userService.findByUsername(username);
//        reviewComment.setId(user.getId());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();

        User user = userService.findByUsername(username);

        reviewComment.setUserId(user.getId());
        reviewComment.setReviewId(reviewId);

        //작성 시간
        reviewComment.setCreatedAt(LocalDateTime.now());
        //후기 댓글 추가
        reviewCommentMapper.reviewCommentInsert(reviewComment);
//        return reviewCommentMapper.selectById(reviewComment.getId());
    }

    //후기에 대한 댓글 수정
    public void updateReviewComment(ReviewComment reviewComment) {
        reviewCommentMapper.reviewCommentUpdate(reviewComment);
    }

    //후기에 대한 댓글 삭제
    public void deleteReviewComment(Long id) {
        reviewCommentMapper.deleteReviewComment(id);
    }
}
