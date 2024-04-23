package com.planningpoker.model;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserModel {
    @Id
    private ObjectId id;
    private String username;
    private String roomCode;
    @Nullable
    private int currentVote;

    public UserModel(String username, String roomCode) {
        this.username = username;
        this.roomCode = roomCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setRoom(RoomModel room) {
        this.setRoom(room);
        System.out.println("New room: " + room);
    }

    public String getUsername() {
        return this.username;
    }

    public String getRoomCode() {
        return this.roomCode;
    }
}
