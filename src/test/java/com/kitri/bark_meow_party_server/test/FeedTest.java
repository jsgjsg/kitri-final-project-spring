//package com.kitri.bark_meow_party_server.test;
//
//import com.kitri.bark_meow_party_server.controller.FeedController;
//import com.kitri.bark_meow_party_server.controller.UserController;
//import com.kitri.bark_meow_party_server.domain.Feed;
//import com.kitri.bark_meow_party_server.domain.FeedHashtag;
//import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
//import com.kitri.bark_meow_party_server.mapper.FeedHashtagMapper;
//import com.kitri.bark_meow_party_server.service.FeedService;
//import com.kitri.bark_meow_party_server.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//public class FeedTest {
//
//    @Autowired
//    private UserController userController;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private FeedController feedController;
//    @Autowired
//    private FeedService feedService;
//    @Autowired
//    FeedHashtagMapper feedHashtagMapper;
//
//    @Test
//    void findAll() throws Exception {
//        // Authenticate a user manually
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//        feedController.save(feed);
//
//        List<FeedDetailDTO> feeds = feedController.getFeeds();
//
//        assertThat(feeds).isNotNull();
//    }
//
//    @Test
//    void creat() throws Exception {
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        Feed feed = new Feed();
//        feed.setImage("Sample Image");
//        feed.setContent("Sample Content");
//        feed.setAnimal("Sample Animal");
//        feedController.save(feed);
//        assertThat(feedController.getFeeds()).isNotNull();
//    }
//
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
