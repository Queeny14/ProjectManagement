package com.example.jira.controller;

import com.example.jira.entity.Issue;
import com.example.jira.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i-api")
public class IssueController {

    @Autowired
    private IssueService issueService ;


    @GetMapping("/issues")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Issue>> findAll() {
        List<Issue> issues = issueService.findAll();
        return ResponseEntity.ok().body(issues);
    }


    @GetMapping("/issues/{issueId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Issue> getIssue(@PathVariable int issueId) {
        Issue theIssue =issueService.findById(issueId);

        if(theIssue == null) {
            throw new RuntimeException("issue id not found : "+issueId);
        }
        return ResponseEntity.ok().body(theIssue);
    }

    @PostMapping("/issues")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Issue> addIssue(@RequestBody Issue theIssue) {
        theIssue.setId(0);
        issueService.save(theIssue);
        return ResponseEntity.ok().body(theIssue);
    }

    @PutMapping("/issues/{issueId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Issue> updateIssue(@PathVariable int issueId, @Valid @RequestBody Issue issue) {
        Issue updatedIssue = issueService.updateIssue(issueId, issue);
        return ResponseEntity.ok().body(updatedIssue);
    }

    @DeleteMapping("/issues/{issueId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public Issue deleteIssue(@PathVariable int issueId) {
        Issue theIssue = issueService.findById(issueId);

        if(theIssue == null) {
            throw new RuntimeException("issue id not found : "+issueId);
        }
        issueService.deleteById(issueId);
        return theIssue;
    }

}
