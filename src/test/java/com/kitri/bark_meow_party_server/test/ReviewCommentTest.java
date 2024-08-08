package com.kitri.bark_meow_party_server.test;

import com.kitri.bark_meow_party_server.controller.ReviewCommentController;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.dto.ReviewCommentWithUserDTO;
import com.kitri.bark_meow_party_server.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class ReviewCommentTest {
    @Autowired
    ReviewCommentController reviewCommentController;

    @Autowired
    ReviewService reviewService;
    @Test
    void findByReviewId() throws Exception {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setContent("This is a test");
        Long reviewId = 2L;
        reviewCommentController.addReviewComment(reviewId, reviewComment);
        List<ReviewCommentWithUserDTO> reviewComments = reviewCommentController.getReviewComments(reviewId);
        assertThat(reviewComments).isNotNull();
    }
    @Test
    void createReviewComment() throws Exception {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setContent("This is a test");
        Long reviewId = 2L;
        reviewCommentController.addReviewComment(reviewId, reviewComment);
        assertThat(reviewCommentController.addReviewComment(reviewId, reviewComment)).isNotNull();
    }
    @Test
    void updateReviewComment() throws Exception {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setContent("This is a test");
        Long reviewId = 2L;
        reviewCommentController.addReviewComment(reviewId, reviewComment);
        reviewComment.setContent("This is another test");
        List<ReviewCommentWithUserDTO> reviewComments = reviewCommentController.getReviewComments(reviewId);
        assertThat(reviewCommentController.addReviewComment(reviewId, reviewComment)).isNotNull();
    }
    @Test
    void deleteReviewComment() throws Exception {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setContent("This is a test");
        Long reviewId = 2L;
        reviewCommentController.addReviewComment(reviewId, reviewComment);
        Long commentId = 29L;
        reviewCommentController.deleteReviewComment(reviewId, commentId);
    }
}
