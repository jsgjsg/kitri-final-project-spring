package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.dto.ReviewCommentWithUserDTO;
import com.kitri.bark_meow_party_server.service.ReviewCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //RESTApi 웹 서비스를 만들 때 사용하는 컨트롤러
@RequestMapping("/api")
public class ReviewCommentController {
    @Autowired //ReviewCommentService 자동 의존성 주입
    private ReviewCommentService reviewCommentService;

    //후기에 대한 댓글을 작성 하기 위한 컨트롤러
    @PostMapping("/reviews/{reviewId}/comments")
    public ReviewCommentWithUserDTO addReviewComment(@PathVariable Long reviewId, @RequestBody ReviewComment reviewComment) {
        return reviewCommentService.addReviewComment(reviewId, reviewComment);
    }

    //후기에 대한 댓글을 조회 하기 위한 컨트롤러
    @GetMapping("/reviews/{reviewId}/comments")
    public List<ReviewCommentWithUserDTO> getReviewComments(@PathVariable Long reviewId) {
        //reviewCommentService 작성 한 후기에 대한 댓글 조회 로직을 가져온다.
        return reviewCommentService.getReviewCommentsByReviewId(reviewId);
    }

    //유저 후기댓글을 조회 하기 위한 컨트롤러
    @GetMapping("/reviews/{reviewId}/comments/{userId}")
    public List<ReviewComment> getReviewComments(@PathVariable Long reviewId, @PathVariable Long userId) {
        return reviewCommentService.getReviewCommentsByUserId(userId);
    }

    //후기에 대한 댓글을 수정 하기 위한 컨트롤러
    @PutMapping("/reviews/{reviewId}/comments/{commentId}")
    public ReviewComment updateReviewComment(@RequestBody ReviewComment reviewComment,
                                             @PathVariable long reviewId, @PathVariable long commentId) {
        reviewComment.setReviewId(reviewId);
        reviewComment.setId(commentId);
        //reviewCommentService 작성 한 후기에 대한 댓글 수정 로직을 가져온다.
        reviewCommentService.updateReviewComment(reviewComment);
        return reviewComment;
    }

    //후기에 대한 댓글을 삭제 하기 위한 컨트롤러
    @DeleteMapping("/reviews/{reviewId}/comments/{commentId}")
    public void deleteReviewComment(@PathVariable long reviewId, @PathVariable long commentId) {
        //reviewCommentService 작성 한 후기에 대한 댓글 삭제 로직을 가져온다.
        reviewCommentService.deleteReviewComment(commentId);
    }

}
