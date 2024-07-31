package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (nickname, username, password, location, introduce, image) VALUES (#{nickname}, #{username}, #{password}, #{location}, #{introduce}, #{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Insert("INSERT INTO authorities (user_id, authority) VALUES (#{userId}, #{authority})")
    void insertAuthority(Long userId, String authority);
}
