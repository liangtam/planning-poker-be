package com.planningpoker.repository;

import com.planningpoker.model.IssueModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssueRepository extends MongoRepository<IssueModel, ObjectId> {
}
