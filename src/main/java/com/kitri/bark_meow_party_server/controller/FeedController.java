package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
import com.kitri.bark_meow_party_server.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //페이징
    @GetMapping("/page")
    public Map<String, Object> getFeedsPage(
            @RequestParam (value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Feed> feeds = feedService.getFeeds(page, size);
        int total = feedService.getFeedsCount();
        Map<String, Object> result = new HashMap<>();
        result.put("feeds", feeds);
        result.put("total", total);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    // 특정 ID의 피드를 저장
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody FeedDetailDTO feedDetailDTO) {
        feedService.saveFeed(feedDetailDTO);

        return ResponseEntity.ok("Feed 등록 완료");
    }

    //특정 ID의 피드 수정
    @PutMapping("/{feedId}")
    public void update(@PathVariable Long feedId, @RequestBody FeedDetailDTO feedDetailDTO) {
        feedDetailDTO.getFeedWithUser().setId(feedId);
        feedService.updateFeed(feedDetailDTO);
    }
    // 삭제
    @DeleteMapping("/{feedId}")
    public void delete(@PathVariable Long feedId) {
        feedService.deleteFeedById(feedId);
    }

    //동물분류 // 필요없어짐
    @GetMapping("/animal")
    public List<FeedDetailDTO> getAnimals(@RequestParam String animal) {
        return feedService.findByAnimal(animal);
    }

    //피드검색 - 검색어, 동물 분류 합쳐서.
    @GetMapping("/search")
    public List<FeedDetailDTO> getSearch(@RequestParam String query, @RequestParam String animal) {
        return feedService.findFeedQuery(query, animal);
    }

    @PostMapping("{feedId}/like")
    public ResponseEntity<Integer> likeFeed(@PathVariable Long feedId) {
        return ResponseEntity.ok(feedService.likeFeed(feedId));
    }
}