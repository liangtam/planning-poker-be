package com.planningpoker.model;

import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class IssueModel {
    @Id
    private ObjectId id;
    private String title;
    @Nullable
    private String description;

    public IssueModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
