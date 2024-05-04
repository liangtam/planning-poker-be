package com.planningpoker.controller;

import com.planningpoker.controller.DTO.CreateUserBody;
import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.UserModel;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.service.interfaces.UserService;
import com.planningpoker.utilities.ErrorObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserModel>> getUserById(@PathVariable String id) {
        try {
            return new ResponseEntity<Optional<UserModel>>(userService.getUserById(new ObjectId(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getUsersFromRoom(@RequestParam String roomCode) {
        try {
            List<UserModel> users = roomService.getUsersFromRoom(roomCode);
            return new ResponseEntity(users, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserModel> postUser(@RequestBody CreateUserBody user) {
        try {
            String username = user.getUsername();
            String roomCode = user.getRoomCode();
            if (roomService.isUserInRoom(username, roomCode)) {
                throw new Exception("User with username " + username + " is already in the room " + roomCode + ".");
            }
            UserModel newUser = userService.createUser(username, roomCode);
            roomService.addUserToRoom(roomCode, newUser);
            return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestParam String userId, @RequestParam String roomCode) {
        try {
            ObjectId id = new ObjectId(userId);
            userService.deleteUser(id);
            roomService.deleteUserFromRoom(roomCode, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable ObjectId id, @RequestParam(required = false) String username, @RequestParam(required = false) Integer currentVote) {
        try {
            return new ResponseEntity(userService.updateUser(id, username, currentVote), HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
