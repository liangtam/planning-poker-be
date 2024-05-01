package com.planningpoker.controller;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.UserModel;
import com.planningpoker.controller.DTO.CreateUserBody;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.service.interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserModel>> getSingleUser(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<UserModel>>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserModel> addUser(@RequestBody CreateUserBody user) {
        try {
            UserModel newUser = userService.createUser(user.getUsername(), user.getRoomCode());
            roomService.addUserToRoom(user.getRoomCode(), newUser);
            return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
        } catch (NotFoundException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable ObjectId id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable ObjectId id, @RequestParam String username) {
        try {
            userService.updateUsername(username, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException error) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
