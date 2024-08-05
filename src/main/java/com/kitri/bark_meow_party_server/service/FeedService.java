package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    @Autowired
   private FeedMapper feedMapper;
    @Autowired
    private UserService userService;

    public List<Feed> getAllFeeds() {
        return feedMapper.findAll();
    }
    public Feed getFeedById(Long id) {
        return feedMapper.findById(id);
    }
    public List<Feed> getFeedsByUserId(Long userId) {
        return feedMapper.findByUserId(userId);
    }
    public Feed saveFeed(Feed feed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        feed.setUserId(user.getId());

        feedMapper.insert(feed);
        return feed;
    }
    public void updateFeed(Feed feed) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        feed.setUserId(user.getId());

        feedMapper.update(feed);
    }
    public void deleteFeedById(Long id) {
       feedMapper.delete(id);
    }
}
