package com.kitri.bark_meow_party_server.test;

import com.kitri.bark_meow_party_server.controller.FeedCommentController;
import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.dto.FeedCommentWithUserDTO;
import com.kitri.bark_meow_party_server.service.FeedCommentService;
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
public class FeedCommentTest {
    @Autowired
    private FeedCommentController feedCommentController;
    @Autowired
    FeedCommentService feedCommentService;

    @Test
    void findByFeedId() throws Exception{
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        FeedComment feedComment = new FeedComment();
        feedComment.setContent("This is a test");
        Long feedId = 69L;
        feedCommentController.addFeedComment(feedId, feedComment);
        List<FeedCommentWithUserDTO> feedComments = feedCommentController.getFeedComments(feedId);
        assertThat(feedComments).isNotNull();
    }
    @Test
    void createFeedComment() throws Exception{
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        FeedComment feedComment = new FeedComment();
        feedComment.setContent("This is a test");
        Long feedId = 69L;
        feedCommentController.addFeedComment(feedId, feedComment);
        assertThat(feedCommentController.addFeedComment(feedId, feedComment)).isNotNull();
    }
    @Test
    void updateFeedComment() throws Exception{
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        FeedComment feedComment = new FeedComment();
        feedComment.setContent("This is a test");
        Long feedId = 69L;
        Long feedCommentId = 1L;
        feedCommentController.addFeedComment(feedId, feedComment);
        feedComment.setContent("This is a test");
        feedCommentController.updateFeedComment(feedComment, feedId, feedCommentId);
        List<FeedCommentWithUserDTO> feedComments = feedCommentController.getFeedComments(feedId);
        assertThat(feedComments).isNotNull();
    }
    @Test
    void deleteFeedComment() throws Exception{
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        FeedComment feedComment = new FeedComment();
        feedComment.setContent("This is a test");
        Long feedId = 69L;
        feedCommentController.addFeedComment(feedId, feedComment);
        Long commentId = 1L;
        feedCommentController.deleteFeedComment(feedId, commentId);
    }
}
