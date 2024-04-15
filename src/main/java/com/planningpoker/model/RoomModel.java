package com.planningpoker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="rooms")
public class RoomModel {
    @Id
    private ObjectId id;
    private List<UserModel> users;

    public RoomModel(List<UserModel> users) {
        this.users = users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return this.users;
    }

}
