package com.planningpoker.model;

import com.mongodb.lang.Nullable;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class IssueModel {
    @Id
    private ObjectId id;
    private String title;
    @Nullable
    private String description;
    @Nullable
    private int pointEstimate;
    private String roomCode;

    public IssueModel(String title, String description, String roomCode) {
        this.title = title;
        this.description = description;
        this.roomCode = roomCode;
    }
}
