package com.planningpoker.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateIssueBody {
    private String title;
    private String description;
    private String roomCode;
    private int pointEstimate;
}
