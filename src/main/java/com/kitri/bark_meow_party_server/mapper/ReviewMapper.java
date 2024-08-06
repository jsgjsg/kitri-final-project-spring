package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //모든 후기를 조회
    @Select("SELECT * FROM Review")
    List<Review> selectAll();

    //주어진 ID에 해당하는 후기조회
    @Select("SELECT * FROM review WHERE id = #{id}")
    Review selectById(Long id);

    //주어진 유저의 후기 조회
    @Select("SELECT * FROM review WHERE user_id=#{userId}")
    List<Review> selectByUserId(Long userId);

    //새 후기를 추가
    @Insert("INSERT INTO Review(user_id, item, good, bad, tip, image, repurchase, satisfaction, animal, category ) VALUES (#{userId}, #{item}, #{good}, #{bad}, #{tip}, #{image}, #{repurchase}, #{satisfaction}, #{animal}, #{category})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Review review);

    //해당 후기 정보를 수정
    @Update("UPDATE Review SET user_id=#{userId}, item=#{item}, good=#{good}, bad=#{bad}, tip=#{tip}, image=#{image}, repurchase=#{repurchase}, satisfaction=#{satisfaction}, animal=#{animal}, category=#{category} WHERE id=#{id}")
    void update(Review review);

    //해당 후기 삭제
    @Delete("DELETE FROM Review WHERE id=#{id}")
    void delete(Long id);
}
