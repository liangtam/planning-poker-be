package com.planningpoker.service;

import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;
import com.planningpoker.repository.RoomRepository;
import com.planningpoker.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomModel createRoom(String roomCode) {
        RoomModel room = roomRepository.insert(new RoomModel(roomCode));
        return room;
    }

    @Override
    public boolean doesRoomExist(String roomCode) {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRoom() {
        return false;
    }

    @Override
    public Optional<RoomModel> addUser(UserModel user) {
        return Optional.empty();
    }

    @Override
    public List<UserModel> getUsers(String roomCode) {
        Optional<RoomModel> room = roomRepository.findByRoomCode(roomCode);
        if (room.isPresent()) {
            RoomModel foundRoom = room.get();
            return foundRoom.getUsers();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<RoomModel> getRoomById() {
        return Optional.empty();
    }

    @Override
    public Optional<IssueModel> addIssue(IssueModel issue) {
        return Optional.empty();
    }

    @Override
    public Optional<List<IssueModel>> getIssues() {
        return Optional.empty();
    }

    @Override
    public Optional<List<UserModel>> updateUsers() {
        return Optional.empty();
    }

    @Override
    public Optional<List<IssueModel>> updateIssues() {
        return Optional.empty();
    }
}
