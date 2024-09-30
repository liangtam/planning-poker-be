package com.planningpoker.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateIssueDetailsRequestBody {
    private String title;
    private String description;
    private Integer pointEstimate;
    private String roomCode;
}
