package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ReviewDetailDTO;
import com.kitri.bark_meow_party_server.dto.ReviewWithUserDTO;
import com.kitri.bark_meow_party_server.mapper.CategoryMapper;
import com.kitri.bark_meow_party_server.mapper.ReviewMapper;
import com.kitri.bark_meow_party_server.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired //ReviewMapper 자동 의존성 주입
    ReviewMapper reviewMapper;
    @Autowired //UserService 자동 의존성 주입
    UserService userService;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SearchMapper searchMapper;

    @Transactional
    //DB 후기 테이블 전체 조회
    public List<ReviewDetailDTO> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        List<ReviewWithUserDTO> reviews = reviewMapper.selectAll();
        List<ReviewDetailDTO> reviewDetailDTOS = new ArrayList<>();

        for (ReviewWithUserDTO review : reviews) {
            ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();
            reviewDetailDTO.setReviewWithUser(review);

            reviewDetailDTO.setLikeCount(reviewMapper.countLikeReview(review.getId()));

            boolean isLiked = reviewMapper.likeReview(user.getId(), review.getId());
            reviewDetailDTO.setLiked(isLiked);

            reviewDetailDTOS.add(reviewDetailDTO);
        }
        return reviewDetailDTOS;
    }

    //페이징
    public List<Review> getReviews(int page, int size) {
        int offset = (page - 1) * size;
        return reviewMapper.selectByOffsetAndLimit(offset, size);
    }
    public int getReviewsCount() {
        return reviewMapper.count();
    }

    public Review findById(Long id) {
        return reviewMapper.selectById(id);
    }

    public List<Review> findByUserId(Long userId) {
        return reviewMapper.selectByUserId(userId);
    }

    //후기 작성
    public void create(Review review) {
        //로그인 한 유저로 작성자를 고정 시키기 위한 로직
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        review.setUserId(user.getId());

        //후기 작성
        reviewMapper.insert(review);
    }

    //후기 수정
    public void update(Review review) {
        //로그인 한 유저로 작성자를 고정 시키기 위한 로직
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        review.setUserId(user.getId());

        //후기 수정
        reviewMapper.update(review);
    }

    //후기 삭제
    public void delete(Long id) {
        reviewMapper.delete(id);
    }

    //동물분류
    public List<Review> findByAnimal(String animal) {
        return categoryMapper.selectByAnimal(animal);
    }
    //카테고리 분류
    public List<Review> findByCategory(String category) {
        return categoryMapper.selectByCategory(category);
    }
    //검색
//        List<FeedDetailDTO> feedDetailDTOs = new ArrayList<>();
//        for (FeedWithUserDTO feed : feeds) {
//            FeedDetailDTO feedDetailDTO = new FeedDetailDTO();
//            feedDetailDTO.setFeedWithUser(feed);
//            feedDetailDTO.setLikeCount(feedMapper.getLikeCount(feed.getId()));
//
//            boolean isLiked = feedMapper.existsByUserIdAndFeedId(user.getId(), feed.getId());
//            feedDetailDTO.setLiked(isLiked);
//
//            feedDetailDTOs.add(feedDetailDTO);
//        }
//
//        return feedDetailDTOs;
//    }
    public List<ReviewDetailDTO> searchByQuery(String query, String animal, String category){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        List<ReviewWithUserDTO> reviews;
        if (animal.equals("all") && category.equals("all")) {
            reviews = searchMapper.searchByQuery(query);
        } else if (!animal.equals("all") && category.equals("all")) {
            reviews = searchMapper.searchByReviewQueryAndAnimal(query, animal);
        } else if (animal.equals("all") && !category.equals("all")) {
            reviews = searchMapper.searchByReviewQueryAndCategory(query, category);
        } else {
            reviews = searchMapper.searchByReviewQueryAndAnimalCategory(query, animal, category);
        }

        List<ReviewDetailDTO> reviewDetailDTOS = new ArrayList<>();

        for (ReviewWithUserDTO review : reviews) {
            ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();
            reviewDetailDTO.setReviewWithUser(review);
            reviewDetailDTO.setLikeCount(reviewMapper.countLikeReview(review.getId()));

            boolean isLiked = reviewMapper.likeReview(user.getId(), review.getId());
            reviewDetailDTO.setLiked(isLiked);

            reviewDetailDTOS.add(reviewDetailDTO);
        }
        return reviewDetailDTOS;
    }

    @Transactional
    public int likeReview(Long reviewId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        Long userId = user.getId();

        if(reviewMapper.likeReview(userId, reviewId)){
            reviewMapper.unlike(userId, reviewId);
        } else {
            reviewMapper.like(userId, reviewId);
        }
        return reviewMapper.countLikeReview(reviewId);
    }
}
