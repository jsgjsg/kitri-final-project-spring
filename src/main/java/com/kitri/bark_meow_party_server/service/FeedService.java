package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.FeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {
    @Autowired
   private FeedMapper feedMapper;
    @Autowired
    private UserService userService;

    @Transactional
    public List<FeedDetailDTO> getAllFeeds() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);

        List<FeedWithUserDTO> feeds = feedMapper.findAll();
        List<FeedDetailDTO> feedDetailDTOs = new ArrayList<>();
        for (FeedWithUserDTO feed : feeds) {
            FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
            feedDetailDTO.setFeedWithUser(feed);

            feedDetailDTO.setLikeCount(feedMapper.getLikeCount(feed.getId()));

            boolean isLiked = feedMapper.existsByUserIdAndFeedId(user.getId(), feed.getId());
            feedDetailDTO.setLiked(isLiked);

            feedDetailDTOs.add(feedDetailDTO);
        }
        return feedDetailDTOs;
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

    @Transactional
    public int likeFeed(Long feedId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        long userId = user.getId();

        // 좋아요 누르기 ( 이미 눌렀으면 취소, 안눌렀으면 좋아요)
        if(feedMapper.existsByUserIdAndFeedId(userId, feedId)) feedMapper.unlikeFeed(userId, feedId);
        else feedMapper.likeFeed(userId, feedId);

        return feedMapper.getLikeCount(feedId);
    }
}
