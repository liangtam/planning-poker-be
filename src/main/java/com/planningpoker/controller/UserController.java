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

//    @PostMapping()
//    public ResponseEntity<UserModel> addUser() {
//
//    }
}
