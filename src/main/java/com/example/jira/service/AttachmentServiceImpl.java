package com.example.jira.service;

import com.example.jira.dao.AttachmentRepository;
import com.example.jira.entity.Attachment;
import com.example.jira.exception.CustomNotFoundException;
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

        if(result.isEmpty()){
            throw new CustomNotFoundException("Requested attachment id does not exist");
        }

        Attachment theAttachment = null;
        theAttachment = result.get();
        return theAttachment;


//        Attachment theAttachment = null;
//
//        if (result.isPresent()) {
//            theAttachment = result.get();
//        } else {
//
//            throw new RuntimeException("Did not find attachment id - " + theId);
//        }
//        return theAttachment;
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
