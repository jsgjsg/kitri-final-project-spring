package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ReviewCommentWithUserDTO;
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

    // 특정 후기 ID에 대한 댓글 조회
    public List<ReviewCommentWithUserDTO> getReviewCommentsByReviewId(Long reviewId) {
        return reviewCommentMapper.selectByReviewId(reviewId);
    }

    //특정 유저 후기 댓글 조회
    public List<ReviewComment> getReviewCommentsByUserId(Long userId) {
        return reviewCommentMapper.selectByUserId(userId);
    }

    //후기에 대한 댓글 작성
    public ReviewCommentWithUserDTO addReviewComment(Long reviewId, ReviewComment reviewComment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();

        User user = userService.findByUsername(username);

        ReviewCommentWithUserDTO reviewCommentWithUserDTO = new ReviewCommentWithUserDTO();
        reviewCommentWithUserDTO.setReviewId(reviewId);
        reviewCommentWithUserDTO.setUserId(user.getId());
        reviewCommentWithUserDTO.setContent(reviewComment.getContent());
        reviewCommentWithUserDTO.setCreatedAt(LocalDateTime.now());
        reviewCommentWithUserDTO.setNickname(user.getNickname());

        reviewCommentMapper.reviewCommentInsert(reviewCommentWithUserDTO);
        return reviewCommentWithUserDTO;
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
