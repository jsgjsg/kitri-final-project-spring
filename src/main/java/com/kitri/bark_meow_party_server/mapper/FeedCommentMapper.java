package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedCommentMapper {
    @Select("SELECT * FROM feed_comment")
    List<FeedComment> selectAll();
    @Select("SELECT * FROM feed_comment WHERE id = #{id}")
    FeedComment selectById(Long id);
    @Insert("INSERT INTO FeedComment(feed_id, content, create_at) VALUES (#{feed_id}, #{content}, #{create_at})")
    void feedCommentInsert(FeedComment comment);
    @Update("UPDATE FeedComment SET content=#{content} WHERE id=#{id}")
    void feedCommentUpdate(FeedComment comment);
    @Delete("DELETE FROM feed_comment WHERE id = #{id}")
    void deleteFeedComment(Long id);
}
