package com.planningpoker.repository;

import com.planningpoker.model.RoomModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<RoomModel, ObjectId> {
    Optional<RoomModel> findByRoomCode(String roomCode);
}
