package com.planningpoker.utilities;

import org.bson.types.ObjectId;

public class MessageUtility {
    public String roomNotFoundMessage(String roomCode) {
        return "Room " + roomCode + " not found.";
    }

    public String userNotFoundMessage(ObjectId id) {
        return "User " + id + "not found.";
    }

    public String issueNotFoundMessage(ObjectId id) {
        return "Issue " + id + "not found.";
    }
}
