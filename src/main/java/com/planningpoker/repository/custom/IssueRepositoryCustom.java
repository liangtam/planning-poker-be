package com.planningpoker.repository.custom;

import com.planningpoker.controller.DTO.UpdateIssueDetailsRequestBody;

public interface IssueRepositoryCustom {
    long updateIssueDetailsByIssueId(String issueId, UpdateIssueDetailsRequestBody request);
    void updateIssuePointEstimate(String issueId, Integer pointEstimate);
}
