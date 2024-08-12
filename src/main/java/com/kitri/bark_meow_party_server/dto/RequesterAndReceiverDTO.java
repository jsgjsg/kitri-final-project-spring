package com.kitri.bark_meow_party_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequesterAndReceiverDTO {
    private long id;
    private long requesterId;
    private long receiverId;
}
