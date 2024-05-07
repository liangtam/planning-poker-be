package com.planningpoker.service;

import com.mongodb.client.result.UpdateResult;
import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;
import com.planningpoker.repository.RoomRepository;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.utilities.MessageUtility;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MessageUtility messageUtility;

    @Override
    public RoomModel createRoom(String roomCode) throws Exception {
        if (roomRepository.existsByRoomCode(roomCode)) {
            throw new Exception("Room already exists.");
        }
        RoomModel room = roomRepository.insert(new RoomModel(roomCode));
        return room;
    }

    @Override
    public void deleteRoom(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            roomRepository.deleteByRoomCode(roomCode);
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public boolean isUserInRoom(String username, String roomCode) {
        Query query = new Query(Criteria.where("roomCode").is(roomCode).and("users").elemMatch(Criteria.where("username").is(username)));
        return mongoTemplate.exists(query, RoomModel.class);
    }

    @Override
    public List<UserModel> getUsersFromRoom(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            return foundRoom.getUsers();
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public Optional<RoomModel> getRoomByCode(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            return room;
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public Optional<RoomModel> getRoomById(ObjectId id) {
        return roomRepository.findById(id);
    }

    @Override
    public void addIssueToRoom(IssueModel issue, String roomCode) throws NotFoundException {
        Query query = new Query(Criteria.where("roomCode").is(roomCode));
        Update update = new Update().push("issues").value(issue);

        UpdateResult result = mongoTemplate.updateFirst(query, update, RoomModel.class);

        if (result.getModifiedCount() == 0) {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void deleteIssueFromRoom(ObjectId issueId, String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            List<IssueModel> issues = foundRoom.getIssues();
            int originalNumOfUsers = issues.size();
            issues.removeIf(user -> user.getId().equals(issueId));
            if (issues.size() == originalNumOfUsers) {
                throw new NotFoundException(messageUtility.createIssueNotFoundMessage(issueId));
            }
            foundRoom.setIssues(issues);
            roomRepository.save(foundRoom);
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }


    @Override
    public List<IssueModel> getIssuesFromRoom(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            return foundRoom.getIssues();
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void addUserToRoom(String roomCode, UserModel user) throws NotFoundException {
        UpdateResult result = mongoTemplate.update(RoomModel.class)
                .matching(Criteria.where("roomCode").is(roomCode))
                .apply(new Update().push("users").value(user))
                .first();
        if (result.getModifiedCount() == 0) {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void deleteUserFromRoom(String roomCode, ObjectId userId) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            List<UserModel> users = foundRoom.getUsers();
            int originalNumOfUsers = users.size();
            users.removeIf(user -> user.getId().equals(userId));
            if (users.size() == originalNumOfUsers) {
                throw new NotFoundException(messageUtility.createUserNotFoundMessage(userId));
            }
            foundRoom.setUsers(users);
            roomRepository.save(foundRoom);
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void updateIssueInRoom(String roomCode, IssueModel updatedIssue) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            ObjectId issueId = updatedIssue.getId();
            List<IssueModel> issues = foundRoom.getIssues();
            int originalNumOfUsers = issues.size();
            issues.removeIf(user -> user.getId().equals(issueId));
            if (issues.size() == originalNumOfUsers) {
                throw new NotFoundException(messageUtility.createIssueNotFoundMessage(issueId));
            }
            issues.add(updatedIssue);
            foundRoom.setIssues(issues);
            roomRepository.save(foundRoom);
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void updateUserInRoom(String roomCode, UserModel updatedUser) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            ObjectId userId = updatedUser.getId();
            List<UserModel> users = foundRoom.getUsers();
            int originalNumOfUsers = users.size();
            users.removeIf(user -> user.getId().equals(userId));
            if (users.size() == originalNumOfUsers) {
                throw new NotFoundException(messageUtility.createUserNotFoundMessage(userId));
            }
            users.add(updatedUser);
            foundRoom.setUsers(users);
            roomRepository.save(foundRoom);
        } else {
            throw new NotFoundException(messageUtility.createRoomNotFoundMessage(roomCode));
        }
    }

}
