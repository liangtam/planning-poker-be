package com.planningpoker.service;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.UserModel;
import com.planningpoker.repository.UserRepository;
import com.planningpoker.service.interfaces.UserService;
import com.planningpoker.utilities.MessageUtility;
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

    @Autowired
    private MessageUtility messageUtility;

    @Override
    // "Optional" is in case we can't find the user and return null
    public Optional<UserModel> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel createUser(String username, String roomCode) {
        return userRepository.insert(new UserModel(username, roomCode));
    }

    @Override
    public void deleteUser(ObjectId id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserModel updateUser(ObjectId id, String username, Integer currVotes) throws NotFoundException {
        Optional<UserModel> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            UserModel user = foundUser.get();
            if (username != null) {
                user.setUsername(username);
            }
            if (currVotes != null) {
                user.setCurrentVote(currVotes);
            }
            return userRepository.save(user);
        } else {
            throw new NotFoundException(messageUtility.createUserNotFoundMessage(id));
        }
    }
}
