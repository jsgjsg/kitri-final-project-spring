package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedLike {
    private long id;
    private long userId;
    private long feedId;

    public FeedLike(final long userId, final long feedId) {
        this.userId = userId;
        this.feedId = feedId;
    }
}
