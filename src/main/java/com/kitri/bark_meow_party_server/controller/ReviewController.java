package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/reviews?category={category}")
    public String getReviews(@PathVariable String category) {
        return "reviews";
    }
    @GetMapping("/reviews/search?query={query}")
    public String getSearchReviews(@PathVariable String query, Model model) {
        model.addAttribute("query", query);
        return "search";
    }
}
