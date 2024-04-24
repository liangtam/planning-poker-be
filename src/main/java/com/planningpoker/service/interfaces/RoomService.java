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
    List<UserModel> getUsers(String roomCode);
    Optional<RoomModel> getRoomById();
    Optional<IssueModel> addIssue(IssueModel issue);
    Optional<List<IssueModel>> getIssues();
    Optional<List<UserModel>> updateUsers();
    Optional<List<IssueModel>> updateIssues();
}
