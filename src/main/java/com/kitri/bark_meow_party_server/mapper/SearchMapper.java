package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Feed;
import com.kitri.bark_meow_party_server.domain.QA;
import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.FeedWithUserDTO;
import com.kitri.bark_meow_party_server.dto.ReviewDetailDTO;
import com.kitri.bark_meow_party_server.dto.ReviewWithUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {
    //주어진 쿼리를 포함하는 후기 조회
    @Select("SELECT * FROM Review WHERE " +
            "item LIKE CONCAT('%', #{query}, '%') OR " +
            "good LIKE CONCAT('%', #{query}, '%') OR " +
            "bad LIKE CONCAT('%', #{query}, '%') OR " +
            "tip LIKE CONCAT('%', #{query}, '%')")
    List<ReviewWithUserDTO> searchByQuery(String query);
    //주어진 쿼리를 포함하는 피드 조회
    @Select("SELECT * " +
            "FROM feed f " +
            "JOIN user u " +
            "ON f.user_id = u.id " +
            "WHERE content LIKE CONCAT('%', #{query}, '%')")
    List<FeedWithUserDTO> searchByFeedQuery(String query);

    //주어진 쿼리를 포함하는 피드 조회 | 동물 있는 경우.
    @Select("SELECT * " +
            "FROM feed f " +
            "JOIN user u " +
            "ON f.user_id = u.id " +
            "WHERE content LIKE CONCAT('%', #{query}, '%') " +
            "AND animal = #{animal}")
    List<FeedWithUserDTO> searchByFeedQueryAndAnimal(String query, String animal);

    //주어진 쿼리를 포함하는 qa 조회
    @Select("SELECT * FROM qa WHERE title LIKE CONCAT('%', #{query}, '%')")
    List<QA> searchByQaQuery(String query);
}
