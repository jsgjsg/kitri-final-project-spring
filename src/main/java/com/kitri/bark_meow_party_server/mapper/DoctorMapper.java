package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DoctorMapper {
    @Select("SELECT * FROM doctor WHERE doctorname = #{doctorname}")
    Doctor findByDoctorname(String doctorname);


//    @Insert("INSERT INTO user (nickname, username, password, location, introduce, image) VALUES (#{nickname}, #{username}, #{password}, #{location}, #{introduce}, #{image})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(User user);
//    @Insert("INSERT INTO authorities (user_id, authority) VALUES (#{userId}, #{authority})")
//    void insertAuthority(Long userId, String authority);
    @Insert("INSERT INTO doctor (nickname, doctorname, password, image, hospital) VALUES (#{nickname}, #{doctorname}, #{password}, #{image}, #{hospital})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Doctor doctor);
    
//    @Select("SELECT username FROM user")
//    List<String> findAllUsernames();
//
//    @Select("SELECT nickname FROM user")
//    List<String> findAllNicknames();
}
