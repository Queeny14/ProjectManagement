package com.example.jira.service;

import com.example.jira.entity.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> findAll();

    public Comment findById(int theId);

    public void save(Comment theComment);

    //public Comment updateComment(int theId, Comment commentDetails);

    public void deleteById(int theId);
}

