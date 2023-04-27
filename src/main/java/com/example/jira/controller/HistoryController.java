package com.example.jira.controller;

import com.example.jira.entity.History;
import com.example.jira.entity.Issue;
import com.example.jira.entity.User;
import com.example.jira.service.HistoryService;
import com.example.jira.service.IssueService;
import com.example.jira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i-api/hist")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/history")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<History>> findAll() {
        List<History> history = historyService.findAll();
        return ResponseEntity.ok().body(history);
    }


    @GetMapping("/history/{historyId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<History> getHistory(@PathVariable int historyId) {
        History theHistory = historyService.findById(historyId);

        if(theHistory == null) {
            throw new RuntimeException("issue id not found : "+historyId);
        }
        return ResponseEntity.ok().body(theHistory);
    }

    @PostMapping("/history")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<History> addComment(@RequestBody History theHistory) {

        Issue theIssue = issueService.findById(theHistory.getIssue().getId());

        if(theIssue == null) {
            throw new RuntimeException("issue id not found : "+ theHistory.getIssue().getId());
        }

        User changedBy = userService.findById(theHistory.getChangedBy().getId());

        if(changedBy == null){
            throw new RuntimeException("author id not found : "+ theHistory.getChangedBy().getId());
        }

        theHistory.setIssue(theIssue);
        theHistory.setChangedBy(changedBy);
        historyService.save(theHistory);
        return ResponseEntity.ok().body(theHistory);
    }

    @DeleteMapping("/history/{historyId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public History deleteComment(@PathVariable int historyId) {
        History theHistory = historyService.findById(historyId);

        if(theHistory == null) {
            throw new RuntimeException("history id not found : "+ historyId);
        }
        historyService.deleteById(historyId);
        return theHistory;
    }


}
