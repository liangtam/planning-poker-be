package com.planningpoker.repository;

import com.planningpoker.model.IssueModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends MongoRepository<IssueModel, ObjectId> {
    void deleteAllByRoomCode(String roomCode);
}
