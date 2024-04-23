package com.planningpoker.service.interfaces;

import com.planningpoker.model.IssueModel;

import java.util.Optional;

public interface IssueService {
    Optional<IssueModel> createIssue(String title, String description);
    boolean deleteIssue();
    Optional<IssueModel> updateIssue(String title, String description, int pointEstimate);
}
