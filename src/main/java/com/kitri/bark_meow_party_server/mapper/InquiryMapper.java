package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Inquiry;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.InquiryWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InquiryMapper {
    @Select("SELECT * FROM inquiry i " +
            "JOIN user u " +
            "ON i.user_id = u.id " +
            "ORDER BY created_at DESC ")
    List<InquiryWithUserDTO> getInquiryAll();

    @Select("SELECT * FROM inquiry ORDER BY created_at DESC WHERE id=#{id}")
    Inquiry getByInquiryId(Long id);

    //페이징 및 최신순
    @Select("SELECT * FROM inquiry ORDER BY created_at DESC LIMIT #{offset}, #{limit}")
    List<Inquiry> selectByOffsetAndLimit(@Param("offset") int offset, @Param("limit") int limit);
    @Select("SELECT COUNT(*) FROM inquiry")
    int count();

    @Insert("INSERT INTO inquiry (user_id, image, title, inquiry, created_at) VALUES (#{userId}, #{image}, #{title}, #{inquiry}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertInquiry(Inquiry inquiry);

    @Update("UPDATE inquiry SET user_id = #{userId}, image = #{image}, title = #{title}, inquiry = #{inquiry} WHERE id = #{id}")
    void updateInquiry(Inquiry inquiry);

    @Delete("DELETE FROM inquiry WHERE id = #{id}")
    void deleteInquiry(Long id);
}
