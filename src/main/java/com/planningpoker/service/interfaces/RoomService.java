package com.planningpoker.service.interfaces;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.model.UserModel;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    RoomModel createRoom(String roomCode) throws Exception;

    void deleteRoom(String roomCode) throws NotFoundException;
    boolean isUserInRoom(String username, String roomCode);
    List<UserModel> getUsersFromRoom(String roomCode) throws NotFoundException;
    Optional<RoomModel> getRoomByCode(String roomCode) throws NotFoundException;
    Optional<RoomModel> getRoomById(ObjectId id);
    void addIssueToRoom(IssueModel issue, String roomCode) throws NotFoundException;
    void deleteIssueFromRoom(ObjectId issueId, String roomCode) throws NotFoundException;
    void updateIssueInRoom(String roomCode, IssueModel updatedIssue) throws NotFoundException;
    List<IssueModel> getIssuesFromRoom(String roomCode) throws NotFoundException;
    void addUserToRoom(String roomCode, UserModel user) throws NotFoundException;
    void deleteUserFromRoom(String roomCode, ObjectId userId) throws NotFoundException;
    void updateUserInRoom(String roomCode, UserModel updatedUser) throws NotFoundException;
}
