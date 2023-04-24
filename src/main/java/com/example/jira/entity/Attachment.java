package com.example.jira.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="attachments")
public class Attachment extends Parent {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="attached_by", referencedColumnName = "id")
    private User attachedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issue_id")
    private Issue issue;

    @Column(name="file_name")
    private String filename;

    @Column(name="file_type")
    private String fileType;

    @Column(name="file_path")
    private String filePath;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    public Attachment(){

    }

    public Attachment(User attachedBy, Issue issue, String filename, String fileType, String filePath, LocalDateTime createdDate) {
        this.attachedBy = attachedBy;
        this.issue = issue;
        this.filename = filename;
        this.fileType = fileType;
        this.filePath = filePath;
        this.createdDate = createdDate;
    }


    public User getAttachedBy() {
        return attachedBy;
    }

    public void setAttachedBy(User attachedBy) {
        this.attachedBy = attachedBy;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                ", attachedBy=" + attachedBy +
                ", issue=" + issue +
                ", filename='" + filename + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
