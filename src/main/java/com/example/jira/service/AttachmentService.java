package com.example.jira.service;

import com.example.jira.entity.Attachment;

import java.util.List;

public interface AttachmentService {

    public List<Attachment> findAll();

    public Attachment findById(int theId);

    public void save(Attachment theAttachment);

    public void deleteById(int theId);
}
