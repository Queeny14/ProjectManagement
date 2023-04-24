package com.example.jira.service;

import com.example.jira.entity.Project;

import java.util.List;


public interface ProjectService {

    public List<Project> findAll();

    public Project findById(int theId);

    public void save(Project theProject);

    public Project updateProject(int theId, Project projectDetails);

    public void deleteById(int theId);
}
