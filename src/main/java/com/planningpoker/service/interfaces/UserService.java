package com.planningpoker.service.interfaces;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.UserModel;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface UserService {
    Optional<UserModel> getUserById(ObjectId id);
    UserModel createUser(String username, String roomCode);
    void deleteUser(ObjectId id);
    UserModel updateUser(ObjectId id, String username, Integer currVotes) throws NotFoundException;
    void deleteAllUsersInRoom(String roomCode);
}
