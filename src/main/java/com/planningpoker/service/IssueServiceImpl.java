package com.planningpoker.service;

import com.planningpoker.model.IssueModel;
import com.planningpoker.repository.IssueRepository;
import com.planningpoker.service.interfaces.IssueService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public IssueModel createIssue(String title, String description) {
        return issueRepository.insert(new IssueModel(title, description));
    }

    @Override
    public void deleteIssue(ObjectId id) {
        issueRepository.deleteById(id);
    }

    @Override
    public Optional<IssueModel> updateIssue(ObjectId id, String title, String description, int pointEstimate) {
        return Optional.empty();
    }
}
