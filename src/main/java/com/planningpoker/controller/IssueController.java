package com.planningpoker.controller;

import com.planningpoker.controller.DTO.IssueBody;
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

@RestController
@RequestMapping("api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/{id}")
    public ResponseEntity getIssueById(@PathVariable String id) {
        try {
            return new ResponseEntity(issueService.getIssueById(new ObjectId(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity getIssuesFromRoom(@RequestParam String roomCode) {
        try {
            List<IssueModel> issues = roomService.getIssuesFromRoom(roomCode);
            return new ResponseEntity(issues, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteIssue(@RequestParam ObjectId issueId, @RequestParam String roomCode) {
        try {
            issueService.deleteIssue(issueId);
            roomService.deleteIssueFromRoom(issueId, roomCode);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity postIssue(@RequestBody IssueBody issue) {
        try {
            IssueModel createdIssue = issueService.createIssue(issue.getTitle(), issue.getDescription(), issue.getRoomCode());
            roomService.addIssueToRoom(createdIssue, issue.getRoomCode());
            return new ResponseEntity(createdIssue, HttpStatus.CREATED);

        } catch (NotFoundException e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Not found"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateIssue(@PathVariable ObjectId id, @RequestBody IssueBody newIssueDetails) {
        try {
            IssueModel updatedIssue = issueService.updateIssue(id, newIssueDetails.getTitle(), newIssueDetails.getDescription(), newIssueDetails.getPointEstimate());
            return new ResponseEntity(updatedIssue, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ErrorObject(e.getMessage(), "Unknown"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
