package com.kitri.bark_meow_party_server.test;

import com.kitri.bark_meow_party_server.controller.FeedCommentController;
import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.FeedCommentWithUserDTO;
import com.kitri.bark_meow_party_server.service.FeedCommentService;
import com.kitri.bark_meow_party_server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class FeedCommentTest {
    @Autowired
    private FeedCommentController controller;
    @Autowired
    FeedCommentService service;

    @Test
    void findByFeedId() throws Exception{
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "test", "test", Collections.emptyList());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, "test", Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        FeedComment feedComment = new FeedComment();
        feedComment.setContent("This is a test");
        Long feedId = 1L;
        controller.addFeedComment(feedId, feedComment);
        List<FeedCommentWithUserDTO> feedComments = controller.getFeedComments(feedId);
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
        Long feedId = 1L;
        controller.addFeedComment(feedId, feedComment);
        assertThat(controller.addFeedComment(feedId, feedComment)).isNotNull();
    }

//    @Test
//    void updateFeedComment() throws Exception{
//        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//                "test", "test", Collections.emptyList());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userDetails, "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        FeedComment feedComment = new FeedComment();
//        feedComment.setContent("This is a test");
////        Feed savedFeed = feedController.save(feed); // assuming save returns the saved feed with ID
////        Long feedId = savedFeed.getId();
//        Long commentId = 1L;
//        Long feedId = 1L;
//        feedComment.setId(commentId);
//        feedComment.setFeedId(feedId);
//        feedComment.setContent("This is a test");
//        assertThat(controller.updateFeedComment(commentId, feedId, feedComment)).isNotNull();
//    }
//    @Test
//    void update() throws Exception {
//        // Authenticate a user manually
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Create and save a new feed to get its ID
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//
//        // Save the feed and obtain its ID
//        Feed savedFeed = feedController.save(feed); // assuming save returns the saved feed with ID
//        Long feedId = savedFeed.getId();
//
//        // Update the feed
//        feed.setId(feedId); // Set the ID for the update
//        feed.setContent("Updated Content");
//        feedController.update(feedId, feed);
//
//        // Fetch the updated feeds and verify the update
//        List<FeedDetailDTO> feeds = feedController.getFeeds();
//
//        // Ensure that we are checking the content inside FeedWithUserDTO
//        assertThat(feeds)
//                .isNotEmpty()
//                .extracting("feedWithUser") // Extract FeedWithUserDTO
//                .extracting("content")      // Extract content from FeedWithUserDTO
//                .contains("Updated Content");
//    }
//
//    @Test
//    void delete() throws Exception {
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//        Feed savedFeed = feedController.save(feed); // assuming save returns the saved feed with ID
//        Long feedId = savedFeed.getId();
//        feedController.delete(feedId);
//    }
//}

}
