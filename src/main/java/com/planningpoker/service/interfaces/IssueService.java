package com.planningpoker.service.interfaces;

import com.planningpoker.model.IssueModel;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface IssueService {
    IssueModel createIssue(String title, String description);
    void deleteIssue(ObjectId id);

    Optional<IssueModel> updateIssue(ObjectId id, String title, String description, int pointEstimate);
}
