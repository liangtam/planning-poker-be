package com.planningpoker.service;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;
import com.planningpoker.repository.RoomRepository;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.utilities.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
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
    public RoomModel createRoom(String roomCode) {
        RoomModel room = roomRepository.insert(new RoomModel(roomCode));
        return room;
    }

    @Override
    public boolean doesRoomExist(String roomCode) {
        return roomRepository.existsByRoomCode(roomCode);
    }

    @Override
    public void deleteRoom(String roomCode) throws NotFoundException {
        if (roomRepository.existsByRoomCode(roomCode)) {
            roomRepository.deleteByRoomCode(roomCode);
        } else {
            throw new NotFoundException(messageUtility.roomNotFoundMessage(roomCode));
        }
    }

    @Override
    public List<UserModel> getUsersFromRoom(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            return foundRoom.getUsers();
        } else {
            throw new NotFoundException(messageUtility.roomNotFoundMessage(roomCode));
        }
    }

    @Override
    public Optional<RoomModel> getRoomByCode(String roomCode) {
        return roomRepository.findByRoomCode(roomCode);
    }

    @Override
    public void addIssueToRoom(IssueModel issue, String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            mongoTemplate.update(RoomModel.class)
                    .matching(Criteria.where("roomCode").is(roomCode))
                    .apply(new Update().push("issues").value(issue))
                    .first();
        } else {
            throw new NotFoundException(messageUtility.roomNotFoundMessage(roomCode));
        }
    }

    @Override
    public List<IssueModel> getIssues(String roomCode) throws NotFoundException {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            return foundRoom.getIssues();
        } else {
            throw new NotFoundException(messageUtility.roomNotFoundMessage(roomCode));
        }
    }

    @Override
    public void addUserToRoom(String roomCode, UserModel user) throws NotFoundException{
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            mongoTemplate.update(RoomModel.class)
                    .matching(Criteria.where("roomCode").is(roomCode))
                    .apply(new Update().push("users").value(user))
                    .first();
        } else {
            throw new NotFoundException(messageUtility.roomNotFoundMessage(roomCode));
        }
    }
}
