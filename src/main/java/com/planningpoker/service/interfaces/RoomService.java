package com.planningpoker.service.interfaces;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomModel createRoom(String roomCode);

    boolean doesRoomExist(String roomCode);

    void deleteRoom(String roomCode) throws NotFoundException;
    Optional<RoomModel> addUser(UserModel user);
    List<UserModel> getUsers(String roomCode) throws Exception;
    Optional<RoomModel> getRoomByCode(String roomCode);
    void addIssue(IssueModel issue, String roomCode) throws NotFoundException;
    List<IssueModel> getIssues(String roomCode) throws NotFoundException;
    List<UserModel> updateUsers();
    List<IssueModel> updateIssues();
}
