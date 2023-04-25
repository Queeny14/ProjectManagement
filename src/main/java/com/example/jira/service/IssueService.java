package com.example.jira.service;

import com.example.jira.entity.Issue;

import java.util.List;

public interface IssueService {

    public List<Issue> findAll();

    public Issue findById(int theId);

    public void save(Issue theIssue);

    public Issue updateIssue(int theId, Issue issueDetails);

    public void deleteById(int theId);
}
