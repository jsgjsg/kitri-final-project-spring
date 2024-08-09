package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user (nickname, username, password, location, introduce, image) VALUES (#{nickname}, #{username}, #{password}, #{location}, #{introduce}, #{image})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Insert("INSERT INTO authorities (user_id, authority) VALUES (#{userId}, #{authority})")
    void insertAuthority(Long userId, String authority);

    @Select("SELECT username FROM user")
    List<String> findAllUsernames();

    @Select("SELECT nickname FROM user")
    List<String> findAllNicknames();

//    @Insert("INSERT INTO user (nickname, username, password, location, introduce, image) VALUES (#{nickname}, #{username}, #{password}, #{location}, #{introduce}, #{image})")
//  @Update("UPDATE Review SET user_id=#{userId}, item=#{item}, good=#{good}, bad=#{bad}, tip=#{tip}, image=#{image}, repurchase=#{repurchase}, satisfaction=#{satisfaction}, animal=#{animal}, category=#{category} WHERE id=#{id}")

    @Update("UPDATE user SET location=#{location}, introduce=#{introduce}, image=#{image} WHERE id=#{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void update(ProfileResponseDTO profileResponseDTO);

    @Update("UPDATE user SET password=#{password} WHERE id=#{id}")
    void updatePassword(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteById(Long id);
}
