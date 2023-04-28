package com.example.jira.controller;

import com.example.jira.entity.Issue;
import com.example.jira.entity.Workflow;
import com.example.jira.exception.CustomNotFoundException;
import com.example.jira.service.IssueService;
import com.example.jira.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i-api/wf")
public class WorkflowController {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IssueService issueService;

    @GetMapping("/workflow")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Workflow>> findAll() {
        List<Workflow> workFlow = workflowService.findAll();
        return ResponseEntity.ok().body(workFlow);
    }


    @GetMapping("/workflow/{workFlowId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Workflow> getWorkflow(@PathVariable int workFlowId) {
        Workflow theWorkflow = workflowService.findById(workFlowId);
        return ResponseEntity.ok().body(theWorkflow);
    }

    @PostMapping("/workflow")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Workflow> addWorkflow(@RequestBody Workflow theWorkflow) {

        Issue theIssue = issueService.findById(theWorkflow.getIssue().getId());

        if(theIssue == null) {
            throw new CustomNotFoundException("issue id not found ");
        }

        theWorkflow.setIssue(theIssue);
        workflowService.save(theWorkflow);
        return ResponseEntity.ok().body(theWorkflow);
    }

    @DeleteMapping("/workflow/{workFlowId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public Workflow deleteWorkflow(@PathVariable int workFlowId) {
        Workflow theWorkflow = workflowService.findById(workFlowId);

        if(workflowService == null) {
            throw new CustomNotFoundException("attachment id not found ");
        }
        workflowService.deleteById(workFlowId);
        return theWorkflow;
    }


}
