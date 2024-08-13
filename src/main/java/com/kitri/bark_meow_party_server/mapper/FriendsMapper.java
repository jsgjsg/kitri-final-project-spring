package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import com.kitri.bark_meow_party_server.dto.RequesterAndReceiverDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendsMapper {

    // 친구 목록 조회
    @Select("SELECT * FROM user " +
            "WHERE id IN ( " +
                "SELECT user1_id FROM friend " +
                "WHERE user2_id=#{userId}) " +
            "OR id IN ( " +
                "SELECT user2_id FROM friend " +
                "WHERE user1_id=#{userId})")
    List<ProfileResponseDTO> findAllFriends(Long userId);

    // 친구 삭제
    @Delete("DELETE FROM friend WHERE user1_id=#{user1Id} AND user2_id=#{user2Id}")
    void deleteFriend(Long user1Id, Long user2Id);

    // 친구 요청
    @Insert("INSERT INTO friend_request (requester_id, receiver_id) VALUES (#{requesterId}, #{receiverId})")
    void requestFriend(@Param("requesterId") Long requesterId, @Param("receiverId") Long receiverId);

    // 보낸 요청 조회
    @Select("SELECT * FROM friend_request fr " +
            "JOIN user u " +
            "ON fr.receiver_id = u.id " +
            "WHERE requester_id=#{requesterId}")
    List<ProfileResponseDTO> getRequestFriend(@Param("requesterId") long requesterId);

    // 보낸 요청 취소
    @Delete("DELETE FROM friend_request WHERE id=#{requestId}")
    void deleteRequestFriend(Long requestId);

    // 받은 요청 조회
    @Select("SELECT * FROM friend_request fr " +
            "JOIN user u " +
            "ON fr.requester_id = u.id " +
            "WHERE receiver_id=#{receiverId}")
    List<ProfileResponseDTO> getReceivedFriend(long receiverId);

    // id로 요청 1개 조회
    @Select("SELECT requester_id, receiver_id FROM friend_request WHERE id=#{requestId}")
    RequesterAndReceiverDTO getRequesterAndReceiver(long requestId);

    // 친구 관계 등록
    @Insert("INSERT INTO friend (user1_id, user2_id) VALUES (#{user1Id}, #{user2Id})")
    void makeFriend(Long user1Id, Long user2Id);

    // 친구 관계 검색
    @Select("SELECT COUNT(*) > 0 FROM friend WHERE user1_id = #{user1Id} AND user2_id = #{user2Id}")
    boolean existsFriendship(long user1Id, long user2Id);

    // 친구 관계 검색
    @Select("SELECT COUNT(*) > 0 FROM friend_request WHERE requester_id = #{requesterId} AND receiver_id = #{receiverId}")
    boolean existsRequest(long requesterId, long receiverId);

}