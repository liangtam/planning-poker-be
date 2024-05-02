package com.planningpoker.service;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.repository.IssueRepository;
import com.planningpoker.service.interfaces.IssueService;
import com.planningpoker.utilities.MessageUtility;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private MessageUtility messageUtility;

    public Optional<IssueModel> getIssueById(ObjectId id) {
        return issueRepository.findById(id);
    }

    @Override
    public IssueModel createIssue(String title, String description, String roomCode) {
        return issueRepository.insert(new IssueModel(title, description, roomCode));
    }

    @Override
    public void deleteIssue(ObjectId id) {
        issueRepository.deleteById(id);
    }

    @Override
    public IssueModel updateIssue(ObjectId id, String title, String description, int pointEstimate) throws NotFoundException{
        Optional<IssueModel> foundIssue = issueRepository.findById(id);
        if (foundIssue.isPresent()) {
            IssueModel issue = foundIssue.get();
            issue.setTitle(title);
            issue.setDescription(description);
            issue.setPointEstimate(pointEstimate);
            return issueRepository.save(issue);
        } else {
            throw new NotFoundException(messageUtility.createIssueNotFoundMessage(id));
        }
    }

    @Override
    public IssueModel updateIssuePoints(ObjectId id, int pointEstimate) throws NotFoundException{
        Optional<IssueModel> foundIssue = issueRepository.findById(id);
        if (foundIssue.isPresent()) {
            IssueModel issue = foundIssue.get();
            issue.setPointEstimate(pointEstimate);
            return issueRepository.save(issue);
        } else {
            throw new NotFoundException(messageUtility.createIssueNotFoundMessage(id));
        }
    }
}
