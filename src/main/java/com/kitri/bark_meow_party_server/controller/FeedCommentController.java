package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.service.FeedCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedCommentController {
    @Autowired
    private FeedCommentService feedCommentService;
    @PostMapping("/feeds/{feedId}/comments")
    public FeedComment addFeedComment(@RequestBody FeedComment feedComment) {
        feedCommentService.addFeedComment(feedComment);
        return feedComment;
    }
    @GetMapping("/feeds/{feedId}/comments")
    public List<FeedComment> getFeedComments(@PathVariable int feedId) {
        return feedCommentService.getFeedComments();
    }
    @PutMapping("/feeds/{feedId}/comments/{commentId}")
    public FeedComment updateFeedComment(@RequestBody FeedComment feedComment,
                                             @PathVariable long feedId, @PathVariable long commentId) {
        feedComment.setFeedId(feedId);
        feedCommentService.updateFeedComment(feedComment);
        return feedComment;
    }
    @DeleteMapping("/feeds/{feedId}/comments/{commentId}")
    public void deleteFeedComment(@PathVariable long feedId, @PathVariable long commentId) {
        feedCommentService.deleteFeedComment(commentId);
    }
}
