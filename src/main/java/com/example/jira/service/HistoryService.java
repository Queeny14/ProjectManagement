package com.example.jira.service;

import com.example.jira.entity.History;

import java.util.List;

public interface HistoryService {

    public List<History> findAll();

    public History findById(int theId);

    public void save(History theHistory);

    public void deleteById(int theId);
}

