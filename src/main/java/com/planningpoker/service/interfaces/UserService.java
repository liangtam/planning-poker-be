package com.planningpoker.service.interfaces;

import com.planningpoker.model.UserModel;
import org.bson.types.ObjectId;

import java.util.Optional;

public interface UserService {
    Optional<UserModel> findUserById(ObjectId id);
    UserModel createUser(String username, String roomCode);
    void deleteUser(ObjectId id);
}
