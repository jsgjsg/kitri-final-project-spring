package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.FeedDetailDTO;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.CategoryMapper;
import com.kitri.bark_meow_party_server.mapper.FeedHashtagMapper;
import com.kitri.bark_meow_party_server.mapper.FeedMapper;
import com.kitri.bark_meow_party_server.mapper.SearchMapper;
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
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private FeedHashtagMapper feedHashtagMapper;

    @Transactional
    public List<FeedDetailDTO> getAllFeeds() {

        List<FeedWithUserDTO> feeds = feedMapper.findAll();
        return makeFeedDetailDTO(feeds);
    }

    public Feed getFeedById(Long id) {
        return feedMapper.findById(id);
    }

    public List<Feed> getFeedsByUserId(Long userId) {
        return feedMapper.findByUserId(userId);
    }

    //페이징
    public List<Feed> getFeeds(int page, int size) {
        int offset = (page - 1) * size;
        return feedMapper.findByOffsetAndLimit(offset, size);
    }
    public int getFeedsCount() {
        return feedMapper.count();
    }

    public void saveFeed(FeedDetailDTO feedDetailDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        feedDetailDTO.getFeedWithUser().setUserId(user.getId());

        feedMapper.insert(feedDetailDTO.getFeedWithUser());

        feedDetailDTO.getFeedHashtags().forEach((tag) -> {
            tag.setFeedId(feedDetailDTO.getFeedWithUser().getId());
            feedHashtagMapper.insertFeedHashtag(tag);
        });
    }

    public void updateFeed(FeedDetailDTO feedDetailDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        feedDetailDTO.getFeedWithUser().setUserId(user.getId());

        feedMapper.update(feedDetailDTO.getFeedWithUser());

        feedHashtagMapper.deleteFeedHashtag(feedDetailDTO.getFeedWithUser().getId());
        feedDetailDTO.getFeedHashtags().forEach((tag) -> {
            tag.setFeedId(feedDetailDTO.getFeedWithUser().getId());
            feedHashtagMapper.insertFeedHashtag(tag);
        });
    }

    public void deleteFeedById(Long id) {
       feedMapper.delete(id);
    }

    //동물분류
    public List<FeedDetailDTO> findByAnimal(String animal) {
        List<FeedWithUserDTO> feeds = categoryMapper.selectByFeedAnimal(animal);
        return makeFeedDetailDTO(feeds);
    }

    //피드검색
    public List<FeedDetailDTO> findFeedQuery(String query, String animal) {
        List<FeedWithUserDTO> feeds;
        if(animal.equals("all")) feeds = searchMapper.searchByFeedQuery(query);
        else feeds = searchMapper.searchByFeedQueryAndAnimal(query, animal);
        return makeFeedDetailDTO(feeds);
    }

    public List<FeedDetailDTO> makeFeedDetailDTO(List<FeedWithUserDTO> feeds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        List<FeedDetailDTO> feedDetailDTOs = new ArrayList<>();
        for (FeedWithUserDTO feed : feeds) {
            FeedDetailDTO feedDetailDTO = new FeedDetailDTO();

            feedDetailDTO.setFeedWithUser(feed);
            feedDetailDTO.setLikeCount(feedMapper.getLikeCount(feed.getId()));
            feedDetailDTO.setLiked(feedMapper.existsByUserIdAndFeedId(user.getId(), feed.getId()));
            feedDetailDTO.setFeedHashtags(feedHashtagMapper.findFeedHashtagByFeedId(feed.getId()));

            feedDetailDTOs.add(feedDetailDTO);
        }

        return feedDetailDTOs;
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
