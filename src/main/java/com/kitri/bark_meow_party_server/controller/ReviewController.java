package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) {
        reviewService.create(review);
        return review;
    }

    @GetMapping("/reviews")
    public List<Review> getReviews() {
        reviewService.findAll();
        return reviewService.findAll();
    }
    @PutMapping("/reviews/{reviewId}")
    public Review updateReview(@PathVariable long reviewId, @RequestBody Review review) {
        review.setId(reviewId);
        reviewService.update(review);
        return review;
    }
    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) {
        reviewService.delete(reviewId);
    }
//    @GetMapping("/reviews?category={category}")
//    public String getReviews(@PathVariable String category) {
//        return "reviews";
//    }
    @GetMapping("/reviews/category")
    public List<Review> getReviewsByCategory(@RequestParam String category) {
        return reviewService.findByCategory(category);
    }
//    @GetMapping("/reviews/search?query={query}")
//    public String getSearchReviews(@PathVariable String query, Model model) {
//        model.addAttribute("query", query);
//        return "search";
//    }
    @GetMapping("/reviews/search")
    public List<Review> getReviewsBySearch(@RequestParam String query) {
        return reviewService.findByQuery(query);
    }
//    private Long getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        }
//        return null; // 현재 사용자가 인증되지 않은 경우, null을 반환합니다.
//    }
}
