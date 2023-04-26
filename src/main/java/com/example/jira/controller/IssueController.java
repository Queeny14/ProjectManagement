package com.example.jira.controller;
import com.example.jira.entity.Issue;
import com.example.jira.entity.Project;
import com.example.jira.entity.User;
import com.example.jira.service.IssueService;
import com.example.jira.service.ProjectService;
import com.example.jira.service.UserService;
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

    @Autowired
    private ProjectService projectService ;

    @Autowired
    private UserService userService ;


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

        Project project = projectService.findById(theIssue.getProject().getId());

        if(project == null) {
            throw new RuntimeException("project id not found : "+theIssue.getProject().getId());
        }

        User reporter = userService.findById(theIssue.getReportTo().getId());

        if(reporter == null){
            throw new RuntimeException("reporter id not found : "+theIssue.getReportTo().getId());
        }

        User assignee = userService.findById(theIssue.getAssignedTo().getId());

        if(assignee == null){
            throw new RuntimeException("assignee id not found : "+theIssue.getAssignedTo().getId());
        }

//        theIssue.setId(0);
//        theIssue.setProject(theIssue.getProject());
        theIssue.setProject(project);
//        theIssue.setReportTo(theIssue.getReportTo());
        theIssue.setReportTo(reporter);
//        theIssue.setAssignedTo(theIssue.getReportTo());
        theIssue.setAssignedTo(assignee);

//        theIssue.setProjectName(project.getName());
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
