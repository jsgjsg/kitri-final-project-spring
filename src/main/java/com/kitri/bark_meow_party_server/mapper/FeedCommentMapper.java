package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedCommentMapper {

    //모든 피드에 대한 댓글 조회
    @Select("SELECT * FROM feed_comment")
    List<FeedComment> selectAll();

    //주어진 ID에 해당하는 피드에 대한 댓글 조회
    @Select("SELECT * FROM feed_comment WHERE id = #{id}")
    FeedComment selectById(Long id);

    //피드에 대한 댓글 추가
    @Insert("INSERT INTO FeedComment(feed_id, content, create_at) VALUES (#{feed_id}, #{content}, #{create_at})")
    void feedCommentInsert(FeedComment comment);

    //주어진 ID에 해당하는 피드에 대한 댓글 수정
    @Update("UPDATE FeedComment SET content=#{content} WHERE id=#{id}")
    void feedCommentUpdate(FeedComment comment);

    //주어진 ID에 해당하는 피드에 대한 댓글 삭제
    @Delete("DELETE FROM feed_comment WHERE id = #{id}")
    void deleteFeedComment(Long id);
}
