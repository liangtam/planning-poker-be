package com.planningpoker.service.interfaces;

import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Optional<RoomModel> createRoom(String roomCode, List<UserModel> users);
    boolean deleteRoom();
    Optional<RoomModel> addUser(UserModel user);
    Optional<List<UserModel>> getUsers();
    Optional<RoomModel> getRoomById();
    Optional<IssueModel> addIssue(IssueModel issue);
    Optional<List<IssueModel>> getIssues();
    Optional<List<UserModel>> updateUsers();
    Optional<List<IssueModel>> updateIssues();
}
