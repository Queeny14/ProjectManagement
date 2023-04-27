package com.example.jira.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@JsonIdentityInfo(
        scope = User.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends Parent {


    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    @JsonIgnore
    private String password;

//    @OneToMany(mappedBy = "projectManager", cascade = CascadeType.ALL)
//    private List<Project> projects = new ArrayList<>();



//    @OneToMany(mappedBy = "reportTo", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//    CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Issue>  reportingIssues = new ArrayList<>();
//
//    @OneToMany(mappedBy = "assignedTo", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Issue> assignedIssues = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="user_roles",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

//    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Comment> comments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "changedBy",  cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    private List<History> history = new ArrayList<>();

//    @OneToMany(mappedBy = "attachedBy",  cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    private List<Attachment> attachments = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH,
//            CascadeType.MERGE,CascadeType.REFRESH})
//    @JoinTable(name = "users_projects",
//                joinColumns = @JoinColumn(name = "project_id"),
//                inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<Project> projects = new ArrayList<>();
//    public List<Issue> getReportingIssues() {
//        return reportingIssues;
//    }

//    public List<Comment> getComments() {
//        return comments;
//    }


    public User(){

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, List<Issue> reportingIssues, List<Issue> assignedIssues, Set<Role> roles, List<Project> projects) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
//        this.reportingIssues = reportingIssues;
//        this.assignedIssues = assignedIssues;
        //this.roles = roles;
       // this.projects = projects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

//    public void setReportingIssues(List<Issue> reportingIssues) {
//        this.reportingIssues = reportingIssues;
//    }
//
//    public List<Issue> getAssignedIssues() {
//        return assignedIssues;
//    }
//
//    public void setAssignedIssues(List<Issue> assignedIssues) {
//        this.assignedIssues = assignedIssues;
//    }

//    public List<History> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<History> history) {
//        this.history = history;
//    }

//    public List<Attachment> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<Attachment> attachments) {
//        this.attachments = attachments;
//    }


//    public List<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(List<Project> projects) {
//        this.projects = projects;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


}
