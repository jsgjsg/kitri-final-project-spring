package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {

    //모든 후기에 대한 댓글 조회
    @Select("SELECT * FROM review_comment")
    List<ReviewComment> selectAll();

    //주어진 ID에 해당하는 후기에 대한 댓글 조회
    @Select("SELECT * FROM review_comment WHERE id = #{id}")
    ReviewComment selectById(Long id);

    //후기에 대한 댓글 추가
    @Insert("INSERT INTO ReviewComment(review_id, user_id, content, create_at) VALUES (#{review_id}, #{user_id}, #{content}, #{create_at}}, #{satisfaction})")
    void reviewCommentInsert(ReviewComment comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 수정
    @Update("UPDATE ReviewComment SET user_id=#{user_id}, content=#{content} WHERE id=#{id}")
    void reviewCommentUpdate(ReviewComment comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 삭제
    @Delete("DELETE FROM review_comment WHERE id = #{id}")
    void deleteReviewComment(Long id);
}
