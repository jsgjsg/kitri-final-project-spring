package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.dto.QAWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QAMapper {
    @Select("SELECT * FROM qa q " +
            "JOIN user u " +
            "ON q.user_id = u.id")
    List<QAWithUserDTO> selectAll();
//    @Select("SELECT * FROM review r " +
//            "JOIN user u " +
//            "ON r.user_id = u.id ")
//    List<ReviewWithUserDTO> selectAll();
    @Select("SELECT * FROM qa WHERE title = #{title}")
    QA selectByTitle(String title);
    @Select("SELECT * FROM qa WHERE user_id=#{userId}")
    List<QA> selectByUserId(Long userId);
    //    @Select("SELECT * " +
//            "FROM review_comment r " +
//            "JOIN user u " +
//            "ON r.user_id = u.id " +
//            "WHERE review_id = #{reviewId}")
//    List<ReviewCommentWithUserDTO> selectByReviewId(Long reviewId);
    @Select("SELECT * FROM qa WHERE id=#{id}")
    QA selectById(Long id);
    @Insert("INSERT INTO qa(user_id, title, created_at) VALUES (#{userId}, #{title}, Now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Long QaInsert(QA qa);
    @Update("UPDATE qa SET title=#{title} WHERE id=#{id}")
    void QaUpdate(QA qa);
    @Delete("DELETE FROM qa WHERE id=#{id}")
    void QaDelete(Long id);
}
