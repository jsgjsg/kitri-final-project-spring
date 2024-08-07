package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewLike {
    private Long id;
    private Long userId;
    private Long reviewId;

    public ReviewLike(final Long userId, final Long reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }
}
