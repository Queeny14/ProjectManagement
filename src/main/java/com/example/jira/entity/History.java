package com.example.jira.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issue_id")
    private Issue issue;

    @Column(name="field_name")
    private String fieldName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="changed_by")
    private User changedBy;

    @Column(name="changed_date")
    private LocalDateTime changedDate;

    public History(){

    }

    public History(Issue issue, String fieldName, User changedBy, LocalDateTime changedDate) {
        this.issue = issue;
        this.fieldName = fieldName;
        this.changedBy = changedBy;
        this.changedDate = changedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(LocalDateTime changedDate) {
        this.changedDate = changedDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", issue=" + issue +
                ", fieldName='" + fieldName + '\'' +
                ", changedBy=" + changedBy +
                ", changedDate=" + changedDate +
                '}';
    }
}
