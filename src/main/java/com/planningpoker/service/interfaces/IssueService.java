package com.planningpoker.service.interfaces;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import org.bson.types.ObjectId;

public interface IssueService {
    IssueModel createIssue(String title, String description);
    void deleteIssue(ObjectId id);
    IssueModel updateIssue(ObjectId id, String title, String description, int pointEstimate) throws NotFoundException;

    IssueModel updateIssuePoints(ObjectId id, int pointEstimate) throws NotFoundException;
}
