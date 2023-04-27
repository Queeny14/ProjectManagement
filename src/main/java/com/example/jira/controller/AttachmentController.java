package com.example.jira.controller;

import com.example.jira.entity.Attachment;
import com.example.jira.entity.Issue;
import com.example.jira.entity.User;
import com.example.jira.service.AttachmentService;
import com.example.jira.service.IssueService;
import com.example.jira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i-api/att")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/attachments")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Attachment>> findAll() {
        List<Attachment> attachments = attachmentService.findAll();
        return ResponseEntity.ok().body(attachments);
    }


    @GetMapping("/attachments/{attachmentId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Attachment> getAttachment(@PathVariable int attachmentId) {
        Attachment theAttachment = attachmentService.findById(attachmentId);

        if(theAttachment == null) {
            throw new RuntimeException("issue id not found : "+attachmentId);
        }
        return ResponseEntity.ok().body(theAttachment);
    }

    @PostMapping("/attachments")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Attachment> addAttachment(@RequestBody Attachment theAttachment) {

        Issue theIssue = issueService.findById(theAttachment.getIssue().getId());

        if(theIssue == null) {
            throw new RuntimeException("issue id not found : "+ theAttachment.getIssue().getId());
        }

        User attachedBy = userService.findById(theAttachment.getAttachedBy().getId());

        if(attachedBy == null){
            throw new RuntimeException("author id not found : "+ theAttachment.getAttachedBy().getId());
        }

        theAttachment.setIssue(theIssue);
        theAttachment.setAttachedBy(attachedBy);
        attachmentService.save(theAttachment);
        return ResponseEntity.ok().body(theAttachment);
    }

    @DeleteMapping("/attachments/{attachmentId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public Attachment deleteComment(@PathVariable int attachmentId) {
        Attachment theAttachment = attachmentService.findById(attachmentId);

        if(attachmentService == null) {
            throw new RuntimeException("attachment id not found : "+ attachmentId);
        }
        attachmentService.deleteById(attachmentId);
        return theAttachment;
    }
}
