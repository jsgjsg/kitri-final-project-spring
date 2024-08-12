package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Question;
import com.kitri.bark_meow_party_server.dto.QuestionWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("SELECT * FROM question")
    List<Question> selectAll();
    @Select("SELECT * FROM question WHERE id=#{id}")
    Question selectById(Long id);
    @Select("SELECT * FROM question WHERE user_id=#{userId}")
    List<Question> selectByUserId(Long userId);
    @Select("SELECT * " +
            "FROM question q " +
            "JOIN user u " +
            "ON q.user_id = u.id " +
            "WHERE qa_id = #{qaId}")
    List<QuestionWithUserDTO> selectByQaId(Long qaId);
    @Insert("INSERT INTO question (user_id, qa_id, question) VALUES (#{userId}, #{qaId}, #{question})")
    void questionInsert(Question question);
    @Update("UPDATE question SET question=#{question} WHERE id=#{id}")
    void questionUpdate(Question question);
    @Delete("DELETE FROM question WHERE qa_id=#{id}")
    void questionDelete(Long id);
}
