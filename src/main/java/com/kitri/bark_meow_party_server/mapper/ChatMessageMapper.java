package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import com.kitri.bark_meow_party_server.dto.ChatRoomsDTO;
import com.kitri.bark_meow_party_server.dto.ProfileResponseDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM chat_message")
    List<ChatMessage> findAll();

    @Select("SELECT * FROM chat_message WHERE room_id = #{roomId} ORDER BY timestamp ASC")
    List<ChatMessage> getMessagesByRoomId(String roomId);

    @Insert("INSERT INTO chat_message (room_id, message, sender, timestamp) VALUES (#{roomId}, #{message}, #{sender}, #{timestamp})")
    void insertMessage(ChatMessage chatMessage);

//    // 친구 목록 조회
//    @Select("SELECT * FROM user " +
//            "WHERE id IN ( " +
//            "SELECT user1_id FROM friend " +
//            "WHERE user2_id=#{userId}) " +
//            "OR id IN ( " +
//            "SELECT user2_id FROM friend " +
//            "WHERE user1_id=#{userId})")
//    List<ProfileResponseDTO> findAllFriends(Long userId);

    // 채팅방 목록 조회해야 함.
    @Select("SELECT u.*, f.id AS roomId " +
            "FROM user u " +
            "JOIN friend f ON ( " +
            "    (f.user1_id = u.id AND f.user2_id = #{userId}) OR " +
            "    (f.user2_id = u.id AND f.user1_id = #{userId}) " +
            ") " +
            "WHERE u.id != #{userId}")
    List<ChatRoomsDTO> getRooms(Long userId);

}
