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

    public IssueModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
