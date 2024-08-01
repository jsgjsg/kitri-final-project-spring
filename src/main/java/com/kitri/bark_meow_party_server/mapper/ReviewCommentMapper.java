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
    @Select("SELECT * FROM review_comment WHERE review_id = #{reviewId}")
    List<ReviewComment> selectByReviewId(Long reviewId);

    // 특정 댓글 ID로 조회
    @Select("SELECT * FROM review_comment WHERE id = #{id}")
    ReviewComment selectById(Long id);

    //후기에 대한 댓글 추가
    @Insert("INSERT INTO review_comment(review_id, user_id, content, created_at) VALUES (#{reviewId}, #{userId}, #{content}, NOW())")
    void reviewCommentInsert(ReviewComment comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 수정
    @Update("UPDATE review_comment SET content=#{content} WHERE id=#{id}")
    void reviewCommentUpdate(ReviewComment comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 삭제
    @Delete("DELETE FROM review_comment WHERE id = #{id}")
    void deleteReviewComment(Long id);
}
