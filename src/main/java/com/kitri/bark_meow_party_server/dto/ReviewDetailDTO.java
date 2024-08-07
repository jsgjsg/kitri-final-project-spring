package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailDTO {
    private ReviewWithUserDTO reviewWithUser;
    private int likeCount;
    private boolean liked;
}
