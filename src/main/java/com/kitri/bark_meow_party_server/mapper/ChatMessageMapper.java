package com.kitri.bark_meow_party_server.mapper;

import com.kitri.bark_meow_party_server.domain.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM chat_message")
    List<ChatMessage> findAll();

    @Insert("INSERT INTO chat_message (message, sender, timestamp) VALUES (#{message}, #{sender}, #{timestamp})")
    void insertMessage(ChatMessage chatMessage);

}
