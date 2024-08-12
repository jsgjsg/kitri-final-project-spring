package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Inquiry;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InquiryMapper {
    @Select("SELECT * FROM inquiry")
    List<Inquiry> getInquiryAll();

    @Select("SELECT * FROM inquiry WHERE id=#{id}")
    Inquiry getByInquiryId(Long id);

    @Insert("INSERT INTO inquiry (user_id, image, title, inquiry, created_at) VALUES (#{userId}, #{image}, #{title}, #{inquiry}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertInquiry(Inquiry inquiry);

    @Update("UPDATE inquiry SET user_id = #{userId}, image = #{image}, title = #{title}, inquiry = #{inquiry} WHERE id = #{id}")
    void updateInquiry(Inquiry inquiry);

    @Delete("DELETE FROM inquiry WHERE id = #{id}")
    void deleteInquiry(Long id);
}
