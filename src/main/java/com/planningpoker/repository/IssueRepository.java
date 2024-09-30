package com.planningpoker.repository;

import com.planningpoker.model.Issues.Issue;
import com.planningpoker.repository.custom.IssueRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends MongoRepository<Issue, String>, IssueRepositoryCustom {
    void deleteAllByRoomCode(String roomCode);
    Issue findByIssueId(String issueId);
    void deleteByIssueId(String issueId);
}
