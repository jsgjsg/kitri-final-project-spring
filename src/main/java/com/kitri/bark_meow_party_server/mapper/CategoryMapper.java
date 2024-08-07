package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //주어진 카테고리에 해당하는 후기 조회
    @Select("SELECT * FROM Review WHERE category = #{category}")
    List<Review> selectByCategory(String category);
    @Select("SELECT * FROM review WHERE animal = #{animal}")
    List<Review> selectByAnimal(String animal);
    @Select("SELECT * " +
            "FROM feed f " +
            "JOIN user u " +
            "ON f.user_id = u.id " +
            "WHERE animal = #{animal}")
    List<FeedWithUserDTO> selectByFeedAnimal(String animal);
}
