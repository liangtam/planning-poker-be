package com.planningpoker.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "rooms")
public class RoomModel {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String roomCode;
    private List<UserModel> users;
    private List<IssueModel> issues;

    public RoomModel(String roomCode) {
        this.roomCode = roomCode;
        this.issues = new ArrayList<IssueModel>();
        this.users = new ArrayList<UserModel>();
    }
}
