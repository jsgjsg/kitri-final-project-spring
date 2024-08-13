package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.InquiryAnswer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InquiryAnswerMapper {
    @Select("SELECT * FROM inquiry_answer")
    List<InquiryAnswer> getInquiryAnswers();

    @Select("SELECT * FROM inquiry_answer WHERE id=#{id}")
    InquiryAnswer getInquiryAnswerById(Long id);

    @Select("SELECT * FROM inquiry_answer WHERE inquiry_id=#{inquiryId}")
    List<InquiryAnswer> getInquiryIdAnswer(Long inquiryId);

    @Insert("INSERT INTO inquiry_answer (user_id, inquiry_id, inquiry_answer, created_at) VALUES (#{userId}, #{inquiryId}, #{inquiryAnswer}, NOW())")
    void addInquiryAnswer(InquiryAnswer inquiryAnswer);

    @Update("UPDATE inquiry_answer SET inquiry_answer=#{inquiryAnswer} WHERE id=#{id}")
    void updateInquiryAnswer(InquiryAnswer inquiryAnswer);

    @Delete("DELETE FROM inquiry_answer WHERE id=#{id}")
    void deleteInquiryAnswer(Long id);
}
