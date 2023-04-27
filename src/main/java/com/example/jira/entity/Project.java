package com.example.jira.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="projects")
@JsonIdentityInfo(
        scope = Project.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Project extends Parent {

    @Column(name="name", unique = true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="start_date")
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User projectManager;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<Issue> issues = new ArrayList<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinTable(name = "users_projects",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id")
//    )
//
//    private List<User> users = new ArrayList<>();

    public Project(){

    }

    public User getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(User projectManager) {
        this.projectManager = projectManager;
    }

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, User projectManager, List<Issue> issues, List<User> users) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectManager = projectManager;
       // this.issues = issues;
//        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }



//    public List<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(List<Issue> issues) {
//        this.issues = issues;
//    }

//    public List<User> getUsers() {
//        return users;
//    }

//    public void setUsers(List<User> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "Project{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectManager='" + projectManager + '\'' +
                //", issues=" + issues +
                '}';
    }
}
