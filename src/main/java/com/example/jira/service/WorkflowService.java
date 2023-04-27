package com.example.jira.service;

import com.example.jira.entity.Workflow;

import java.util.List;

public interface WorkflowService {

    public List<Workflow> findAll();

    public Workflow findById(int theId);

    public void save(Workflow theWorkflow);

    public Workflow updateWorkflow(int theId, Workflow workflowDetails);

    public void deleteById(int theId);
}
