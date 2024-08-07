package com.kitri.bark_meow_party_server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedHashtag {
    private long id;
    private long feedId;
    private String hashtag;

    public FeedHashtag(long feedId, String hashtag) {
        this.feedId = feedId;
        this.hashtag = hashtag;
    }
}
