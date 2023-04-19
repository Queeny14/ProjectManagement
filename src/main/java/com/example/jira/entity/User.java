package com.example.jira.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="fullName")
    private String fullName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @OneToMany(mappedBy = "reportTo", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
    CascadeType.MERGE,CascadeType.REFRESH})
    private List<Issue>  reportingIssues = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private List<Issue> assignedIssues = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "changedBy",  cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private List<History> history = new ArrayList<>();

    @OneToMany(mappedBy = "attachedBy",  cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "users_projects",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Project> projects = new ArrayList<>();
    public List<Issue> getReportingIssues() {
        return reportingIssues;
    }

    public List<Comment> getComments() {
        return comments;
    }


    public User(){

    }

    public User(String fullName, String email, String password, String role, List<Issue> reportingIssues, List<Issue> assignedIssues, List<Comment> comments, List<History> history, List<Attachment> attachments, List<Project> projects) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.reportingIssues = reportingIssues;
        this.assignedIssues = assignedIssues;
        this.comments = comments;
        this.history = history;
        this.attachments = attachments;
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setReportingIssues(List<Issue> reportingIssues) {
        this.reportingIssues = reportingIssues;
    }

    public List<Issue> getAssignedIssues() {
        return assignedIssues;
    }

    public void setAssignedIssues(List<Issue> assignedIssues) {
        this.assignedIssues = assignedIssues;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }


    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", reportingIssues=" + reportingIssues +
                ", assignedIssues=" + assignedIssues +
                ", comments=" + comments +
                ", history=" + history +
                ", attachments=" + attachments +
                '}';
    }
}
