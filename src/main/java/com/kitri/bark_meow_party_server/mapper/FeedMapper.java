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
    @Select("SELECT * From feed where user_id = #{userId}")
    Feed findById(long userId);

    // 피드 삽입
    @Insert("INSERT INTO feed(user_id, image, content, animal) VALUES (#{userId}, #{image}, #{content}, #{animal})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(Feed feed);

    // 피드 업데이트
    @Update("UPDATE Feed SET image=#{image}, content=#{content}, animal=#{animal} WHERE id=#{id}")
    void update(Feed Feed);

    // 피드 삭제
    @Delete("DELETE from feed where id = #{feedId}")
    void delete(long feedId);
}

