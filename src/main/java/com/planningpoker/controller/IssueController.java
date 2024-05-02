package com.planningpoker.controller;

import com.planningpoker.exceptions.NotFoundException;
import com.planningpoker.model.IssueModel;
import com.planningpoker.service.interfaces.IssueService;
import com.planningpoker.service.interfaces.RoomService;
import com.planningpoker.utilities.ErrorObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/issues/")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity getIssuesFromRoom(@RequestParam String roomCode) {
        try {
            List<IssueModel> issues = roomService.getIssuesFromRoom(roomCode);
            return new ResponseEntity(issues, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIssue(@PathVariable ObjectId id) {
        try {
            Optional<IssueModel> issue = issueService.getIssueById(id);
            issueService.deleteIssue(id);
            roomService.deleteIssueFromRoom(id, issue.get().getRoomCode());
            return new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity postIssue(@RequestBody CreateIssueBody issue, @RequestParam String roomCode) {
        try {
            IssueModel createdIssue = issueService.createIssue(issue.getTitle(), issue.getDescription(), issue.getRoomCode());
            roomService.addIssueToRoom(createdIssue, roomCode);
            return new ResponseEntity(createdIssue, HttpStatus.CREATED);

        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
