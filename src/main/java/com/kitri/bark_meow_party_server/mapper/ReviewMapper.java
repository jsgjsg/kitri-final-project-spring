package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import com.kitri.bark_meow_party_server.dto.ReviewWithUserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //모든 후기를 조회
    @Select("SELECT * FROM review r " +
            "JOIN user u " +
            "ON r.user_id = u.id ")
    List<ReviewWithUserDTO> selectAll();

    //주어진 ID에 해당하는 후기조회
    @Select("SELECT * FROM review WHERE id = #{id}")
    Review selectById(Long id);

    //주어진 유저의 후기 조회
    @Select("SELECT * FROM review WHERE user_id=#{userId}")
    List<Review> selectByUserId(Long userId);

    //페이징
    @Select("SELECT * FROM review LIMIT #{offset}, #{limit}")
    List<Review> selectByOffsetAndLimit(@Param("offset") int offset, @Param("limit") int limit);
    @Select("SELECT COUNT(*) FROM review")
    int count();

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

    //좋아요 눌렀는제 확인
    @Select("SELECT COUNT(*) > 0 FROM review_like WHERE user_id = #{userId} AND review_id = #{reviewId}")
    Boolean likeReview(@Param("userId") Long userId, @Param("reviewId") Long reviewId);

    //좋아요 누르기
    @Select("INSERT INTO review_like(user_id, review_id) VALUES (#{userId}, #{reviewId})")
    void like(@Param("userId") Long userId, @Param("reviewId") Long reviewId);

    // 좋아요 취소
    @Delete("DELETE FROM review_like WHERE user_id = #{userId} AND review_id = #{reviewId}")
    void unlike(@Param("userId") Long userId, @Param("reviewId") Long reviewId);

    // 좋아요 개수 조회
    @Select("SELECT COUNT(*) FROM review_like WHERE review_id = #{reviewId}")
    int countLikeReview(@Param("reviewId") Long reviewId);
}
