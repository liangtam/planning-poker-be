package com.planningpoker.controller;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.model.RoomModel;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.utilities.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private MessageUtility messageUtility;

    @GetMapping("/issues")
    public ResponseEntity<List<IssueModel>> getIssuesFromRoom(@RequestParam String roomCode) {
        try {
            List<IssueModel> issues = roomService.getIssuesFromRoom(roomCode);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (NotFoundException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<Optional<RoomModel>> getRoomByCode(@RequestParam String roomCode) {
        try {
            Optional<RoomModel> room = roomService.getRoomByCode(roomCode);
            return new ResponseEntity<>(room, HttpStatus.OK);
        } catch (NotFoundException error) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<RoomModel> createRoom(@RequestParam String roomCode) {
        try {
            RoomModel room = roomService.createRoom(roomCode);
            return new ResponseEntity<>(room, HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{roomCode}")
    public ResponseEntity deleteRoom(@PathVariable String roomCode) {
        try {
            roomService.deleteRoom(roomCode);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException error) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
