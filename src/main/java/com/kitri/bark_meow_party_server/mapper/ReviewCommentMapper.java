package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.domain.ReviewComment;
import com.kitri.bark_meow_party_server.dto.ReviewCommentWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {

    //모든 후기에 대한 댓글 조회
    @Select("SELECT * FROM review_comment")
    List<ReviewComment> selectAll();

    //주어진 ID에 해당하는 후기에 대한 댓글 조회
    @Select("SELECT * " +
            "FROM review_comment r " +
            "JOIN user u " +
            "ON r.user_id = u.id " +
            "WHERE review_id = #{reviewId}")
    List<ReviewCommentWithUserDTO> selectByReviewId(Long reviewId);

    //특정 유저 조회
    @Select("SELECT * FROM review_comment WHERE user_id=#{userId}")
    List<ReviewComment> selectByUserId(Long userId);
    // 특정 댓글 ID로 조회
    @Select("SELECT * FROM review_comment WHERE id = #{id}")
    ReviewComment selectById(Long id);

    //후기에 대한 댓글 추가
    @Insert("INSERT INTO review_comment(review_id, user_id, content, created_at) VALUES (#{reviewId}, #{userId}, #{content}, NOW())")
    void reviewCommentInsert(ReviewCommentWithUserDTO comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 수정
    @Update("UPDATE review_comment SET content=#{content} WHERE id=#{id}")
    void reviewCommentUpdate(ReviewComment comment);

    //주어진 ID에 해당하는 후기에 대한 댓글 삭제
    @Delete("DELETE FROM review_comment WHERE id = #{id}")
    void deleteReviewComment(Long id);
}
