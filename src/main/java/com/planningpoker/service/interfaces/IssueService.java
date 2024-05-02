package com.planningpoker.service.interfaces;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface IssueService {
    Optional<IssueModel> getIssueById(ObjectId id);
    IssueModel createIssue(String title, String description, String roomCode);
    void deleteIssue(ObjectId id);
    IssueModel updateIssue(ObjectId id, String title, String description, Integer pointEstimate) throws NotFoundException;
    IssueModel updateIssuePoints(ObjectId id, int pointEstimate) throws NotFoundException;
}
