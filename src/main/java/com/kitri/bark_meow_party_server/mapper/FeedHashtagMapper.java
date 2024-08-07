package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.FeedHashtag;
import com.kitri.bark_meow_party_server.domain.QA;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedHashtagMapper {

    // feed의 hashtag 조회
    @Select("SELECT * FROM feed_hashtag WHERE feed_id = #{feedId}")
    List<FeedHashtag> findFeedHashtagByFeedId(@Param("feedId") long feedId);

    // feed의 hashtag 등록
    @Insert("INSERT INTO feed_hashtag(feed_id, hashtag) VALUES (#{feedId}, #{hashtag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long insertFeedHashtag(FeedHashtag feedHashtag);

    // feed의 hashtag 삭제
    @Delete("DELETE FROM feed_hashtag WHERE feed_id = #{feedId}")
    void deleteFeedHashtag(@Param("feedId") long feedId);

}
