package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewCommentController {
    @Autowired
    private ReviewCommentService reviewCommentService;

    @PostMapping("/reviews/{reviewId}/comments")
    public ReviewComment addReviewComment(@RequestBody ReviewComment reviewComment) {
        reviewCommentService.addReviewComment(reviewComment);
        return reviewComment;
}
    @GetMapping("/reviews/{reviewId}/comments")
    public List<ReviewComment> getReviewComments() {
        return reviewCommentService.getReviewComments();
    }
    @PutMapping("/reviews/{reviewId}/comments/{commentId}")
    public ReviewComment updateReviewComment(@RequestBody ReviewComment reviewComment,
                                             @PathVariable long reviewId, @PathVariable long commentId) {
        reviewComment.setReviewId(reviewId);
        reviewCommentService.updateReviewComment(reviewComment);
        return reviewComment;
    }
    @DeleteMapping("/reviews/{reviewId}/comments/{commentId}")
    public void deleteReviewComment(@PathVariable long reviewId, @PathVariable long commentId) {
        reviewCommentService.deleteReviewComment(commentId);
    }

}
