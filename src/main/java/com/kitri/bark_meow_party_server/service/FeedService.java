package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {
    @Autowired
   private FeedMapper feedMapper;

    public List<Feed> getFeeds() {
        return feedMapper.findAll();
    }
    public Feed getFeedById(long id) {
        return feedMapper.findById(id);
    }
    public Feed saveFeed(Feed feed) {
        feedMapper.insert(feed);
        return feed;
    }
    public void updateFeed(Feed feed) {
        feedMapper.update(feed);
    }
    public void deleteFeedById(long feedId) {
       feedMapper.delete(feedId);
    }
}
