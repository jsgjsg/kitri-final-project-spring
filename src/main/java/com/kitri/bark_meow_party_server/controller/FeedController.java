package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import com.kitri.bark_meow_party_server.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    // 모든 피드 목록을 조회
    @GetMapping("")
    public List<FeedDetailDTO> getFeeds() {
        return feedService.getAllFeeds();
    }

    @GetMapping("/{id}")
    public Feed get(@PathVariable Long id) {
        return feedService.getFeedById(id);
    }
    @GetMapping("/user/{userId}")
    public List<Feed> getByUserId(@PathVariable Long userId) {
        return feedService.getFeedsByUserId(userId);
    }

    // 특정 ID의 피드를 저장
    @PostMapping("")
    public Feed save(@RequestBody Feed feed) {
        System.out.println(feed);
        return feedService.saveFeed(feed);
    }

    //특정 ID의 피드 수정
    @PutMapping("/{feedId}")
    public void update(@PathVariable Long feedId, @RequestBody Feed feed) {
        feed.setId(feedId);
        feedService.updateFeed(feed);
    }
    // 삭제
    @DeleteMapping("/{feedId}")
    public void delete(@PathVariable Long feedId) {
        feedService.deleteFeedById(feedId);
    }

    //동물분류
    @GetMapping("/animal")
    public List<Feed> getAnimals(@RequestParam String animal) {
        return feedService.findByAnimal(animal);
    }

    //피드검색
    @GetMapping("/search")
    public List<FeedDetailDTO> getSearch(@RequestParam String query) {
        return feedService.findFeedQuery(query);
    }

    @PostMapping("{feedId}/like")
    public ResponseEntity<Integer> likeFeed(@PathVariable Long feedId) {
        return ResponseEntity.ok(feedService.likeFeed(feedId));
    }
}