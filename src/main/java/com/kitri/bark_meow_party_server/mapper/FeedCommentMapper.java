package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.FeedComment;
import com.kitri.bark_meow_party_server.dto.FeedCommentWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FeedCommentMapper {

    //모든 피드에 대한 댓글 조회
    @Select("SELECT * FROM feed_comment")
    List<FeedComment> selectAll();

    //주어진 ID에 해당하는 피드에 대한 댓글 조회
    @Select("SELECT * " +
            "FROM feed_comment f " +
            "JOIN user u " +
            "ON f.user_id = u.id " +
            "WHERE feed_id = #{feedId}")
    List<FeedCommentWithUserDTO> selectByFeedId(Long feedId);

    //특정 ID 댓글 조회
    @Select("SELECT * FROM feed_comment WHERE id = #{id}")
    FeedComment selectById(Long id);

    //피드에 대한 댓글 추가
    @Insert("INSERT INTO feed_comment(feed_id, user_id, content, created_at) VALUES (#{feedId}, #{userId}, #{content}, NOW())")
    void feedCommentInsert(FeedComment comment);

    //주어진 ID에 해당하는 피드에 대한 댓글 수정
    @Update("UPDATE feed_comment SET content=#{content} WHERE id=#{id}")
    void feedCommentUpdate(FeedComment comment);

    //주어진 ID에 해당하는 피드에 대한 댓글 삭제
    @Delete("DELETE FROM feed_comment WHERE id = #{id}")
    void deleteFeedComment(Long id);
}
