package com.planningpoker.controller.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserBody {
    private String username;
    private String roomCode;

    public CreateUserBody(String username, String roomCode) {
        this.username = username;
        this.roomCode = roomCode;
    }
}
