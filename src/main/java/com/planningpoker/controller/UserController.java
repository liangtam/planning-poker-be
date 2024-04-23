package com.planningpoker.controller;

import com.planningpoker.model.UserModel;
import com.planningpoker.service.UserService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserModel>> getSingleUser(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<UserModel>>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
        return new ResponseEntity<UserModel>(userService.createUser(user.getUsername(), user.getRoom()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable ObjectId id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
