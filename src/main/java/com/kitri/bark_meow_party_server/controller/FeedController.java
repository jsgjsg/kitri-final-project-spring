package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.mapper.FeedMapper;
import com.kitri.bark_meow_party_server.service.FeedService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    // 모든 피드 목록을 조회
    @GetMapping("/")
    public List<Feed> list() {
        return feedService.getFeeds();
    }

    // 특정 피드를 ID를 통해 조회
    @GetMapping("/{id}")
    public Feed get(@PathVariable int id) {
        return feedService.getFeedById(id);
    }

    // 특정 ID의 피드를 저장
    @PostMapping("/")
    public Feed save(Feed feed) {
        return feedService.saveFeed(feed);
    }

    //특정 ID의 피드 수정
    @PutMapping("/{feedId}")
    public void update(@PathVariable long feedId, @RequestBody Feed feed) {
        feed.setId(feedId);
        feedService.updateFeed(feed);
    }

    // 삭제
    @DeleteMapping("/{feedId}")
    public void delete(@PathVariable long feedId) {
        feedService.deleteFeedById(feedId);
    }
}

