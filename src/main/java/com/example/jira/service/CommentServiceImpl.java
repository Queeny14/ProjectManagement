package com.example.jira.service;

import com.example.jira.dao.CommentRepository;
import com.example.jira.entity.Comment;
import com.example.jira.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(int theId) {
        Optional<Comment> result = commentRepository.findById(theId);

        if (result.isEmpty()) {
            throw new CustomNotFoundException("Requested comment id not found");
        }

        Comment theComment = null;
        theComment = result.get();
        return theComment;
    }
//
//        if (result.isPresent()) {
//            theComment = result.get();
//        }
//        else {
//
//            throw new RuntimeException("Did not find comment id - " + theId);
//        }
//        return theComment;


    @Override
    public void save(Comment theComment) {

        commentRepository.save(theComment) ;
    }



    @Override
    public void deleteById(int theId) {
        commentRepository.deleteById(theId);
    }
}
