package com.planningpoker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class UserModel {
    @Id
    private ObjectId id;
    private String username;
    private RoomModel room;

    public UserModel(String username, RoomModel room) {
        this.username = username;
        this.room = room;
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

    public RoomModel getRoom() {
        return this.room;
    }
}
