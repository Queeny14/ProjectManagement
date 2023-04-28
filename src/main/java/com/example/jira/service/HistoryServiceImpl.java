package com.example.jira.service;

import com.example.jira.dao.HistoryRepository;
import com.example.jira.entity.History;
import com.example.jira.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public History findById(int theId) {
        Optional<History> result = historyRepository.findById(theId);

        if (result.isEmpty()) {
            throw new CustomNotFoundException("Requested History does not exist");

        }

        History theHistory = null;
        theHistory = result.get();
        return theHistory;
    }

//        History theHistory = null;
//
//        if (result.isPresent()) {
//            theHistory = result.get();
//        } else {
//
//            throw new RuntimeException("Did not find history id - " + theId);
//        }
//        return theHistory;


    @Override
    public void save(History theHistory) {
        historyRepository.save(theHistory) ;
    }

    @Override
    public void deleteById(int theId) {
        historyRepository.deleteById(theId);
    }
}
