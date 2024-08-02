package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.QA;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QAMapper {
    @Select("SELECT * FROM qa")
    List<QA> selectAll();
    @Select("SELECT * FROM qa WHERE title = #{title}")
    QA selectByTitle(String title);
    @Select("SELECT * FROM qa WHERE user_id=#{userId}")
    QA selectByUserId(Long userId);
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
