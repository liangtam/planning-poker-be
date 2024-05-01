package com.planningpoker.utilities;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MessageUtility {
    public String createRoomNotFoundMessage(String roomCode) {
        return "Room " + roomCode + " not found.";
    }

    public String createUserNotFoundMessage(ObjectId id) {
        return "User " + id + "not found.";
    }

    public String createIssueNotFoundMessage(ObjectId id) {
        return "Issue " + id + "not found.";
    }
}
