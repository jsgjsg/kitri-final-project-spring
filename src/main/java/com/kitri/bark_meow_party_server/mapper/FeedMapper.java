package com.kitri.bark_meow_party_server.mapper;
import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.FeedLike;
import com.kitri.bark_meow_party_server.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedMapper {
    // 피드 전체보기
    @Select("SELECT * FROM Feed")
    List<Feed> findAll();

    // 피드 한 개씩 보기
    @Select("SELECT * From feed where id = #{id}")
    Feed findById(Long id);

    @Select("SELECT * FROM feed WHERE user_id=#{userId}")
    List<Feed> findByUserId(Long userId);

    // 피드 삽입
    @Insert("INSERT INTO feed(user_id, image, content, animal) VALUES (#{userId}, #{image}, #{content}, #{animal})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Feed feed);

    // 피드 업데이트
    @Update("UPDATE feed SET user_id=#{userId}, image=#{image}, content=#{content}, animal=#{animal} WHERE id=#{id}")
    void update(Feed feed);

    // 피드 삭제
    @Delete("DELETE FROM feed WHERE id = #{id}")
    void delete(Long id);

    // 좋아요 여부 확인
    @Select("SELECT COUNT(*) > 0 FROM feed_like WHERE user_id = #{userId} AND feed_id = #{feedId}")
    boolean existsByUserIdAndFeedId(@Param("userId") Long userId, @Param("feedId") Long feedId);

    // 좋아요 누르기
    @Select("INSERT INTO feed_like(user_id, feed_id) VALUES (#{userId}, #{feedId})")
    void likeFeed(@Param("userId") Long userId, @Param("feedId") Long feedId);

    // 좋아요 취소
    @Delete("DELETE FROM feed_like WHERE user_id = #{userId} AND feed_id = #{feedId}")
    void unlikeFeed(@Param("userId") Long userId, @Param("feedId") Long feedId);

    // 좋아요 개수 조회
    @Select("SELECT COUNT(*) FROM feed_like WHERE feed_id = #{feedId}")
    int getLikeCount(@Param("feedId") Long feedId);

}

