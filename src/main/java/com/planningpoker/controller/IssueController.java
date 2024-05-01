package com.planningpoker.controller;

import com.planningpoker.service.interfaces.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues/")
public class IssueController {
    @Autowired
    private IssueService issueService;
}
