package com.planningpoker.service.DTO;

import lombok.Data;

@Data
public class CreateUserBody {
    private String username;
    private String roomCode;

    public CreateUserBody(String username, String roomCode) {
        this.username = username;
        this.roomCode = roomCode;
    }
}
