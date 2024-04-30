package com.planningpoker.service;

import com.planningpoker.model.UserModel;
import com.planningpoker.repository.UserRepository;
import com.planningpoker.service.interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    // can use for stuff implemented in repo
    @Autowired
    private UserRepository userRepository;

    // for more complex/dynamic queries w/o using repo
    @Autowired
    private MongoTemplate mongoTemplate;

    // "Optional" is in case we can't find the user and return null
    public Optional<UserModel> findUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    public UserModel createUser(String username, String roomCode) {
        UserModel newUser = new UserModel(username, roomCode);
        userRepository.insert(newUser);
        return newUser;
    }

    public void deleteUser(ObjectId id) {
            userRepository.deleteById(id);
    }
}
