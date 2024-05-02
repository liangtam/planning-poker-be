package com.planningpoker.controller.DTO;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IssueBody {
    @Nullable
    private String title;
    @Nullable
    private String description;
    @Nullable
    private String roomCode;
    @Nullable
    private Integer pointEstimate;

    // for updating issue details
    public IssueBody(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public IssueBody(Integer pointEstimate) {
        this.pointEstimate = pointEstimate;
    }
}
