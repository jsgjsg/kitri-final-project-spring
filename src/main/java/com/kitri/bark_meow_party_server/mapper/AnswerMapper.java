package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Answer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnswerMapper {
    @Select("SELECT * FROM answer")
    List<Answer> getAll();
    @Select("SELECT * FROM answer WHERE id=#{id}")
    Answer getById(Long id);
    @Select("SELECT * FROM answer WHERE user_id=#{userId}")
    List<Answer> getByDoctorId(Long doctorId);
    @Select("SELECT * FROM answer WHERE qa_id=#{qaId}")
    List<Answer> getByQaId(Long qaId);
    @Insert("INSERT INTO answer (user_id, qa_id, answer) VALUES (#{userId}, #{qaId}, #{answer})")
    void createAnswer(Answer answer);
    @Update("UPDATE answer SET answer = #{answer} WHERE id=#{id}")
    void updateAnswer(Answer answer);
    @Delete("DELETE FROM answer WHERE id=#{id}")
    void deleteAnswer(Long id);
}
