package com.planningpoker.repository.impl;

import com.planningpoker.controller.DTO.UpdateIssueDetailsRequestBody;
import com.planningpoker.model.Issues.Issue;
import com.planningpoker.repository.custom.IssueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;

import static com.planningpoker.model.Issues.QIssue.issue;
import static com.planningpoker.repository.utility.QueryUtils.parseFieldName;
import static org.springframework.data.mongodb.core.query.Criteria.where;

public class IssueRepositoryCustomImpl implements IssueRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long updateIssueDetailsByIssueId(String issueId, UpdateIssueDetailsRequestBody request) {
        Query query = new Query();
        query.addCriteria(where(parseFieldName(issue.issueId)).is(issueId));
        query.addCriteria(where(parseFieldName(issue.roomCode)).is(request.getRoomCode()));

        Update update = new Update();
        if (!ObjectUtils.isEmpty(request.getDescription())) {
            update.set(parseFieldName(issue.description), request.getDescription());
        }
        if (!ObjectUtils.isEmpty(request.getTitle())) {
            update.set(parseFieldName(issue.title), request.getTitle());
        }
        if (!ObjectUtils.isEmpty(request.getPointEstimate())) {
            update.set(parseFieldName(issue.pointEstimate), request.getPointEstimate());
        }

        return mongoTemplate.updateFirst(query, update, Issue.class).getModifiedCount();
    }

    @Override
    public void updateIssuePointEstimate(String issueId, Integer pointEstimate) {
        Query query = new Query();
        query.addCriteria(where(parseFieldName(issue.issueId)).is(issueId));
        Update update = new Update();
        update.set(parseFieldName(issue.pointEstimate), pointEstimate);
        mongoTemplate.updateFirst(query, update, Issue.class);
    }
}
