package com.kitri.bark_meow_party_server.mapper;
import com.kitri.bark_meow_party_server.domain.Feed;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedMapper {
    // 피드 전체보기
    @Select("SELECT * FROM Feed")
    List<Feed> findAll();

    // 피드 한 개씩 보기
    @Select("SELECT * From  Feed where user_id = #{user_id}")
    Feed findById(long user_id);

    // 피드 삽입
    @Insert("INSERT INTO Feed(user_id,image, content,hashtag, animal) VALUES (#{user_id},#{image}, #{content},#{hashtag}, #{animal})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Feed Feed);

    // 피드 업데이트
    @Update("UPDATE Feed set image=#{image}, content=#{content},hashtag=#{hashtag}, animal=#{animal}) where id =#{id}")
    void update(Feed Feed);

    // 피드 삭제
    @Delete("DELETE from feed where id = #{feedId}")
    void delete(long feedId);
}

