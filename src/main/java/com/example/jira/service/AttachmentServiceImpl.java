package com.example.jira.service;

import com.example.jira.dao.AttachmentRepository;
import com.example.jira.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttachmentServiceImpl implements  AttachmentService{

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment findById(int theId) {

        Optional<Attachment> result = attachmentRepository.findById(theId);

        Attachment theAttachment = null;

        if (result.isPresent()) {
            theAttachment = result.get();
        } else {

            throw new RuntimeException("Did not find attachment id - " + theId);
        }
        return theAttachment;
    }

    @Override
    public void save(Attachment theAttachment) {
        attachmentRepository.save(theAttachment);
    }

    @Override
    public void deleteById(int theId) {
        attachmentRepository.deleteById(theId);

    }
}
