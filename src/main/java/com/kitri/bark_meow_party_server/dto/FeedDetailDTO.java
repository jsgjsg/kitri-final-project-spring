package com.kitri.bark_meow_party_server.dto;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.FeedLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDetailDTO {
    private FeedWithUserDTO feedWithUser;
    private int likeCount;
    private boolean isLiked;
}
