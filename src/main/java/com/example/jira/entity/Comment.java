package com.example.jira.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="issue_id")
    private Issue issue;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Comment() {
    }

    public Comment(User author, Issue issue, String comment, LocalDateTime createdDate) {
        this.author = author;
        this.issue = issue;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
