package com.planningpoker.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mongodb.lang.Nullable;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class IssueModel {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String title;
    @Nullable
    private String description;
    @Nullable
    private Integer pointEstimate;
    private String roomCode;

    public IssueModel(String title, String description, String roomCode) {
        this.title = title;
        this.description = description;
        this.roomCode = roomCode;
    }
}
