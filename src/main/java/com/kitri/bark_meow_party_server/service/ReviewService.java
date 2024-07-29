package com.kitri.bark_meow_party_server.service;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    public List<Review> findAll() {
        return reviewMapper.selectAll();
    }
    public Review findById(Long id) {
        return reviewMapper.selectById(id);
    }
    public void create(Review review) {
        reviewMapper.insert(review);
    }
    public void update(Review review) {
        reviewMapper.update(review);
    }
    public void delete(Long id) {
        reviewMapper.delete(id);
    }
}
