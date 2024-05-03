package com.planningpoker.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
