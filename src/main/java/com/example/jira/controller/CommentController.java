package com.example.jira.controller;

import com.example.jira.entity.Comment;
import com.example.jira.entity.Issue;
import com.example.jira.entity.User;
import com.example.jira.exception.CustomNotFoundException;
import com.example.jira.service.CommentService;
import com.example.jira.service.IssueService;
import com.example.jira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/i-api/cmt")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("/comments")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Comment>> findAll() {
        List<Comment> comments = commentService.findAll();
        return ResponseEntity.ok().body(comments);
    }


    @GetMapping("/comments/{commentId}")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Comment> getComment(@PathVariable int commentId) {
        Comment theComment =commentService.findById(commentId);
        return ResponseEntity.ok().body(theComment);
    }

    @PostMapping("/comments")
    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Comment> addComment(@RequestBody Comment theComment) {

        Issue theIssue = issueService.findById(theComment.getIssue().getId());

        if(theIssue == null) {
            throw new CustomNotFoundException("issue id not found ");
        }

        User author = userService.findById(theComment.getAuthor().getId());

        if(author == null){
            throw new CustomNotFoundException("author id not found ");
        }


        theComment.setIssue(theIssue);
        theComment.setAuthor(author);
        commentService.save(theComment);
        return ResponseEntity.ok().body(theComment);
    }

    @DeleteMapping("/comments/{commentId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public Comment deleteComment(@PathVariable int commentId) {
        Comment theComment = commentService.findById(commentId);

        if(theComment == null) {
            throw new CustomNotFoundException("comment id not found ");
        }
        commentService.deleteById(commentId);
        return theComment;
    }

}
