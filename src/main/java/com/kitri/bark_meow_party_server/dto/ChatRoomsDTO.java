package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomsDTO extends ProfileResponseDTO {
    private long roomId;
}
