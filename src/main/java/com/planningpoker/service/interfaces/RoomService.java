package com.planningpoker.service.interfaces;

import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomModel createRoom(String roomCode);

    boolean doesRoomExist(String roomCode);

    boolean deleteRoom();
    Optional<RoomModel> addUser(UserModel user);
    List<UserModel> getUsers(String roomCode) throws Exception;
    Optional<RoomModel> getRoomByCode(String roomCode);
    IssueModel addIssue(IssueModel issue);
    List<IssueModel> getIssues();
    List<UserModel> updateUsers();
    List<IssueModel> updateIssues();
}
