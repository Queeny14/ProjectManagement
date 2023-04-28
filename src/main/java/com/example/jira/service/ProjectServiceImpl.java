package com.example.jira.service;

import com.example.jira.dao.ProjectRepository;
import com.example.jira.entity.Project;
import com.example.jira.exception.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository ;

    @Autowired
    public ProjectServiceImpl(ProjectRepository theProjectRepository){
        projectRepository = theProjectRepository ;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(int theId) {
        Optional<Project> result = projectRepository.findById(theId);

        if(result.isEmpty()){
            throw new CustomNotFoundException("Requested project id does not exist");
        }

        Project theProject = null;
        theProject = result.get();
        return theProject;


//        Optional<Project> result = projectRepository.findById(theId);
//
//        Project theProject = null;
//
//        if (result.isPresent()) {
//            theProject = result.get();
//        }
//        else {
//
//            throw new RuntimeException("Did not find project id - " + theId);
//        }
//        return theProject;
    }

    @Override
    public void save(Project theProject) {
        projectRepository.save(theProject) ;
    }

    @Override
    public Project updateProject(int theId, Project projectDetails) {
        Project project = projectRepository.findById(theId).orElseThrow(() -> new RuntimeException("Project does not exist"));
        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());
        project.setStartDate(projectDetails.getStartDate());
        project.setEndDate(projectDetails.getEndDate());
        project.setProjectManager(projectDetails.getProjectManager());
        return projectRepository.save(project);
    }

    @Override
    public void deleteById(int theId) {
        projectRepository.deleteById(theId);
    }

}
