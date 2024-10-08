package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByDoctorname(String username);

    @Insert("INSERT INTO user (nickname, username, password, image, hospital) VALUES (#{nickname}, #{username}, #{password}, #{image}, #{hospital})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
    @Insert("INSERT INTO authorities (user_id, authority) VALUES (#{userId}, #{authority})")
    void insertAuthority(Long userId, String authority);

    @Select("SELECT username FROM user")
    List<String> getAllDoctorname();

    @Select("SELECT nickname FROM user")
    List<String> getAllNickname();

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Long id);
}
