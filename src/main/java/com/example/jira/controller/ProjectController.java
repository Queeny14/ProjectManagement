package com.example.jira.controller;

import com.example.jira.entity.Project;
import com.example.jira.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/p-api")

public class ProjectController {

    @Autowired
    private ProjectService projectService ;


    @GetMapping("/projects")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<List<Project>> findAll() {
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok().body(projects);
    }


    @GetMapping("/projects/{projectId}")
    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
    public ResponseEntity<Project> getProject(@PathVariable int projectId) {
        Project theProject =projectService.findById(projectId);

        if(theProject == null) {
            throw new RuntimeException("project id not found : "+projectId);
        }
        return ResponseEntity.ok().body(theProject);
    }

    @PostMapping("/projects")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> addProject(@RequestBody Project theProject) {
        theProject.setId(0);
        projectService.save(theProject);
        return ResponseEntity.ok().body(theProject);
    }

    @PutMapping("/projects/{projectId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Project> updateProject(@PathVariable int projectId, @Valid @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return ResponseEntity.ok().body(updatedProject);
    }

    @DeleteMapping("/projects/{projectId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Project deleteProject(@PathVariable int projectId) {
        Project theProject = projectService.findById(projectId);

        if(theProject == null) {
            throw new RuntimeException("project id not found : "+projectId);
        }
        projectService.deleteById(projectId);
        return theProject;
    }

}
