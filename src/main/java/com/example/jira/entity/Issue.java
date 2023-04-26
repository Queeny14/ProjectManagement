package com.example.jira.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="issues")
@JsonIdentityInfo(
        scope = Issue.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Issue extends Parent{


    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name="issue_project_fk"))
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "reporter_id", foreignKey = @ForeignKey(name="issue_reporter_fk"))
    private User reportTo;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "assignee_id", foreignKey = @ForeignKey(name="issue_assignee_fk"))
    private User assignedTo;

    @Column(name = "label")
    @Enumerated(EnumType.STRING)
    private IssueLabel label;

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private IssuePriority priority;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "sprint")
    private String sprint;

//    @OneToOne(mappedBy = "issue", cascade = CascadeType.ALL)
//    private Workflow workflow;
//
//    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
//    private List<Comment> comments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
//    private List<History> history = new ArrayList<>();
//
//    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
//    private  List<Attachment> attachments = new ArrayList<>();

    public Issue() {
    }

    public Issue(Project project, User reportTo, User assignedTo, IssueLabel label, String summary, String description, IssuePriority priority, String status, LocalDateTime createdDate, LocalDateTime updatedDate, String sprint, Workflow workflow, List<Comment> comments, List<History> history, List<Attachment> attachments) {
        this.project = project;
        this.reportTo = reportTo;
        this.assignedTo = assignedTo;
        this.label = label;
        this.summary = summary;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.sprint = sprint;
//        this.workflow = workflow;
//        this.comments = comments;
//        this.history = history;
//        this.attachments = attachments;
    }


    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getReportTo() {
        return reportTo;
    }

    public void setReportTo(User reportTo) {
        this.reportTo = reportTo;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public IssueLabel getLabel() {
        return label;
    }

    public void setLabel(IssueLabel label) {
        this.label = label;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssuePriority getPriority() {
        return priority;
    }

    public void setPriority(IssuePriority priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

//    public Workflow getWorkflow() {
//        return workflow;
//    }
//
//    public void setWorkflow(Workflow workflow) {
//        this.workflow = workflow;
//    }
//
//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
//
//    public List<History> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<History> history) {
//        this.history = history;
//    }
//
//    public List<Attachment> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<Attachment> attachments) {
//        this.attachments = attachments;
    //}

//    public String getProjectName() {
//        return project.getName();
//    }
//
//    public void setProjectName(String projectName) {
//        project.setName(projectName);
//    }
}
