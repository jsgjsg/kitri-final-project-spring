package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendsMapper {

    @Select("SELECT * FROM user " +
            "WHERE id IN ( " +
                "SELECT user1_id FROM friend " +
                "WHERE user2_id=#{userId}) " +
            "OR id IN ( " +
                "SELECT user2_id FROM friend " +
                "WHERE user1_id=#{userId})")
    List<ProfileResponseDTO> findAllFriends(Long userId);

    @Delete("DELETE FROM friend WHERE user1_id=#{user1Id} AND user2_id=#{user2Id}")
    void deleteFriend(Long user1Id, Long user2Id);

    @Insert("INSERT INTO friend_request (requester_id, receiver_id) VALUES (#{requesterId}, #{receiverId})")
    void requestFriend(@Param("requesterId") Long requesterId, @Param("receiverId") Long receiverId);

    @Select("SELECT * FROM friend_request WHERE requester_id=#{requesterId}")
    List<ProfileResponseDTO> getRequestFriend(@Param("requesterId") Long requesterId);

    @Delete("DELETE FROM friend_request WHERE id=#{requestId}")
    void deleteRequestFriend(Long requestId);

    @Select("SELECT * FROM friend_request WHERE receiver_id=#{receiverId}")
    List<ProfileResponseDTO> getReceivedFriend(long receiverId);

}