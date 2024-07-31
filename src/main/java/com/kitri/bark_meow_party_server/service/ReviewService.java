package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    public List<Review> findAll() {
        return reviewMapper.selectAll();
    }
    public Review findById(Long id) {
        return reviewMapper.selectById(id);
    }
    public void create(Review review) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        User user = userService.findByUsername(username);
        review.setId(user.getId());

        reviewMapper.insert(review);
    }
    public void update(Review review) {
        reviewMapper.update(review);
    }
    public void delete(Long id) {
        reviewMapper.delete(id);
    }
    public List<Review> findByCategory(String category) {
        return reviewMapper.selectByCategory(category);
    }
    public List<Review> findByQuery(String query) {
        return reviewMapper.searchByQuery(query);
    }
}
