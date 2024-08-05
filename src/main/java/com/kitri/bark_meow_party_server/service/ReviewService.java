package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired //ReviewMapper 자동 의존성 주입
    ReviewMapper reviewMapper;
    @Autowired //UserService 자동 의존성 주입
    UserService userService;

    //DB 후기 테이블 전체 조회
    public List<Review> findAll() {
        return reviewMapper.selectAll();
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

    //카테고리 분류
    public List<Review> findByCategory(String category) {
        return reviewMapper.selectByCategory(category);
    }

    //검색
    public List<Review> findByQuery(String query) {
        return reviewMapper.searchByQuery(query);
    }
}
