package com.kitri.bark_meow_party_server.test;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.ReviewMapper;
import com.kitri.bark_meow_party_server.service.ReviewService;
import com.kitri.bark_meow_party_server.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ReviewTest {
    @InjectMocks
    private ReviewService reviewService; // 실제 ReviewService 객체를 생성하여 주입합니다.

    @Mock
    private ReviewMapper reviewMapper;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Review testReview = new Review();
        testReview.setItem("Test Item");
        // Mock 설정
        when(reviewMapper.selectAll()).thenReturn(Collections.singletonList(testReview));

        List<Review> reviews = reviewService.findAll();
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getItem()).isEqualTo("Test Item");
    }

    @Test
    void create() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testuser");

        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        when(userService.findByUsername("testuser")).thenReturn(testUser);

        Review review = new Review();
        review.setItem("New Item");
        review.setGood("New Good");
        review.setBad("New Bad");
        review.setTip("New Tip");
        review.setImage("new.jpg");
        review.setRepurchase("No");
        review.setSatisfaction(3.0);
        review.setAnimal("Cat");
        review.setCategory("Toys");
        review.setUserId(testUser.getId()); // Mock된 User의 ID를 사용
        review.setCreatedAt(LocalDateTime.now());

        reviewService.create(review);
        verify(reviewMapper, times(1)).insert(review);
    }

    @Test
    void update() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testuser");

        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        when(userService.findByUsername("testuser")).thenReturn(testUser);

        Review review = new Review();
        review.setItem("Test Item");
        review.setGood("Test Good");
        review.setBad("Test Bad");
        review.setTip("Test Tip");
        review.setImage("test.jpg");
        review.setRepurchase("Yes");
        review.setSatisfaction(4.5);
        review.setAnimal("Dog");
        review.setCategory("Food");
        review.setUserId(testUser.getId()); // Mock된 User의 ID를 사용
        review.setCreatedAt(LocalDateTime.now());

        reviewService.update(review);
        verify(reviewMapper, times(1)).update(review);
    }

    @Test
    void delete() {
        Review review = new Review();
        review.setId(1L); // ID를 설정합니다.

        reviewService.delete(review.getId());
        verify(reviewMapper, times(1)).delete(review.getId());
    }
}
