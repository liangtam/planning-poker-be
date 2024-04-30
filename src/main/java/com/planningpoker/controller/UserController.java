package com.planningpoker.controller;

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
        return new ResponseEntity<Optional<UserModel>>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserModel> addUser(@RequestBody CreateUserBody user) {
        if (roomService.doesRoomExist(user.getRoomCode())) {
            return new ResponseEntity<UserModel>(userService.createUser(user.getUsername(), user.getRoomCode()), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable ObjectId id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
