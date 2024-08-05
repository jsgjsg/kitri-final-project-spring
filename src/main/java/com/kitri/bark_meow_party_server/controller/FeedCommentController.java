package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.dto.FeedCommentWithUserDTO;
import com.kitri.bark_meow_party_server.service.FeedCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//FeedCommentController는 피드 댓글에 대한 HTTP 요청을 처리하는 REST 컨트롤러
@RestController
@RequestMapping("/api")
public class FeedCommentController {
    @Autowired //FeedCommentService 자동 의존성 주입
    private FeedCommentService feedCommentService;
    //피드에 대한 댓글을 작성 하기 위한 컨트롤러
    @PostMapping("/feeds/{feedId}/comments")
    public FeedComment addFeedComment(@PathVariable Long feedId, @RequestBody FeedComment feedComment) {
        feedCommentService.addFeedComment(feedId, feedComment);
        return feedComment;
    }
    //피드에 대한 댓글을 조회 하기 위한 컨트롤러
    @GetMapping("/feeds/{feedId}/comments")
    public List<FeedCommentWithUserDTO> getFeedComments(@PathVariable Long feedId) {
        return feedCommentService.getFeedCommentsByFeedId(feedId);
    }
    //피드에 대한 댓글을 수정 하기 위한 컨트롤러
    @PutMapping("/feeds/{feedId}/comments/{commentId}")
    public FeedComment updateFeedComment(@RequestBody FeedComment feedComment,
                                             @PathVariable long feedId, @PathVariable long commentId) {
        feedComment.setFeedId(feedId);
        feedComment.setId(commentId);
        feedCommentService.updateFeedComment(feedComment);
        return feedComment;
    }
    //피드에 대한 댓글을 삭제 하기 위한 컨트롤러
    @DeleteMapping("/feeds/{feedId}/comments/{commentId}")
    public void deleteFeedComment(@PathVariable long feedId, @PathVariable long commentId) {
        feedCommentService.deleteFeedComment(commentId);
    }
}
