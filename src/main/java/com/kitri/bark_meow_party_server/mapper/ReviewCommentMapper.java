package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {
    @Select("SELECT * FROM review_comment")
    List<ReviewComment> selectAll();
    @Select("SELECT * FROM review_comment WHERE id = #{id}")
    ReviewComment selectById(Long id);
    @Insert("INSERT INTO ReviewComment(review_id, user_id, content, create_at) VALUES (#{review_id}, #{user_id}, #{content}, #{create_at}}, #{satisfaction})")
    void reviewCommentInsert(ReviewComment comment);
    @Update("UPDATE ReviewComment SET user_id=#{user_id}, content=#{content} WHERE id=#{id}")
    void reviewCommentUpdate(ReviewComment comment);
    @Delete("DELETE FROM review_comment WHERE id = #{id}")
    void deleteReviewComment(Long id);
}
