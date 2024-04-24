package com.planningpoker.controller;

import com.planningpoker.model.RoomModel;
import com.planningpoker.service.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<RoomModel> createRoom(@RequestBody String roomCode) {
        System.out.println("HERERERERERERE");
        return new ResponseEntity<RoomModel>(roomService.createRoom(roomCode), HttpStatus.CREATED);
    }
}
