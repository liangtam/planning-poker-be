package com.planningpoker.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="rooms")
public class RoomModel {
    @Id
    private ObjectId id;
    private String roomCode;
    private List<UserModel> users;
    private List<IssueModel> issues;

    public RoomModel(String roomCode) {
        this.roomCode = roomCode;
        this.issues = new ArrayList<IssueModel>();
        this.users = new ArrayList<UserModel>();
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<UserModel> getUsers() {
        return this.users;
    }

    public List<IssueModel> getIssues() {
        return this.issues;
    }
}
