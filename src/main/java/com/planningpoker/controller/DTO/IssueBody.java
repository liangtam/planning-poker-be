package com.planningpoker.controller.DTO;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public IssueBody(String title, String description, String roomCode) {
        this.title = title;
        this.description = description;
        this.roomCode = roomCode;
    }

    public IssueBody(Integer pointEstimate) {
        this.pointEstimate = pointEstimate;
    }
}
