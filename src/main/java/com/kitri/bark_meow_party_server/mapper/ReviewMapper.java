package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Select("SELECT * FROM Review")
    List<Review> selectAll();
    @Select("SELECT * FROM Reivew WHERE id = #{id}")
    Review selectById(Long id);
    @Select("SELECT * FROM Review WHERE category = #{category}")
    List<Review> selectByCategory(String category);
    @Select("SELECT * FROM Review WHERE " +
            "item LIKE CONCAT('%', #{query}, '%') OR " +
            "good LIKE CONCAT('%', #{query}, '%') OR " +
            "bad LIKE CONCAT('%', #{query}, '%') OR " +
            "tip LIKE CONCAT('%', #{query}, '%') OR " +
            "repurchase LIKE CONCAT('%', #{query}, '%') OR " +
            "animal LIKE CONCAT('%', #{query}, '%')")
    List<Review> searchByQuery(String query);
    @Insert("INSERT INTO Review(item, good, bad, tip, image, repurchase, satisfaction) VALUES (#{item}, #{good}, #{bad}, #{tip}, #{image}, #{repurchase}, #{satisfaction})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Review review);
    @Update("UPDATE Review SET item=#{item}, good=#{good}, bad=#{bad}, tip=#{tip}, image=#{image}, repurchase=#{repurchase}, satisfaction=#{satisfaction} WHERE id=#{id}")
    void update(Review review);
    @Delete("DELETE FROM Review WHERE id=#{id}")
    void delete(Long id);
}
