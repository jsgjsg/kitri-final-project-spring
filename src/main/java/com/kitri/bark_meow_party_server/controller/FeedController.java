package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feeds")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("")
    public List<Feed> list() {
        return feedService.getFeeds();
    }
}

