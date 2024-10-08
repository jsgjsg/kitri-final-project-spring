package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.ReviewDetailDTO;
import com.kitri.bark_meow_party_server.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //RESTApi 웹 서비스를 만들 때 사용하는 컨트롤러
@RequestMapping("/api")
public class ReviewController {
    @Autowired //ReviewService 자동 의존성 주입
    private ReviewService reviewService;

    //후기 작성을 하기 위한 컨트롤러
    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review review) { //@RequestBody : 데이터를 자바 객체로 변환
        //reviewService에서 작성 한 후기 작성 로직을 가져온다.
        reviewService.create(review);
        return review;
    }

    //후기 리스트를 가져오기 위한 컨트롤러
    @GetMapping("/reviews")
    public List<ReviewDetailDTO> getReviews() {
        //reviewService에서 작성 한 후기 조회 로직을 가져온다.
        reviewService.findAll();
        return reviewService.findAll();
    }

    @GetMapping("/reviews/{userId}")
    public List<Review> getReview(@PathVariable Long userId) {
        reviewService.findByUserId(userId);
        return reviewService.findByUserId(userId);
    }

    //페이징
    @GetMapping("/reviews/page")
    public Map<String, Object> getReviewsPage(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Review> reviews = reviewService.getReviews(page, size);
        int total = reviewService.getReviewsCount();
        Map<String, Object> result = new HashMap<>();
        result.put("reviews", reviews);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    //후기를 수정하기 위한 컨트롤러
    @PutMapping("/reviews/{reviewId}")
    public Review updateReview(@PathVariable long reviewId, @RequestBody Review review) {
        //review에서 수정하기위해 reviewId 가져온다.
        review.setId(reviewId);
        //reviewService에서 작성 한 후기 수정 로직을 가져온다.
        reviewService.update(review);
        return review;
    }

    //후기를 삭제하기 위한 컨트롤러
    @DeleteMapping("/reviews/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) {
        //reviewService에서 작성 한 후기 삭제 로직을 가져온다.
        reviewService.delete(reviewId);
    }
    //동물분류
    @GetMapping("/reviews/animal")
    public List<Review> getReviewsAnimal(@RequestParam String animal) {
        reviewService.findByAnimal(animal);
        return reviewService.findByAnimal(animal);
    }

    //카테고리 로직
    @GetMapping("/reviews/category")
    public List<Review> getReviewsByCategory(@RequestParam String category) {
        return reviewService.findByCategory(category);
    }
//    @GetMapping("/reviews?category={category}")

    //검색 로직
    @GetMapping("/reviews/search")
    public List<ReviewDetailDTO> getReviewsBySearch(@RequestParam String query, @RequestParam String animal, @RequestParam String category) {
        return reviewService.searchByQuery(query, animal, category);
    }
//    @GetMapping("/reviews/search?query={query}")

    @PostMapping("/reviews/{reviewId}/like")
    public ResponseEntity<Integer> like(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.likeReview(reviewId));
    }
}
