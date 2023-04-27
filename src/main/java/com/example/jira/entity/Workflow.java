package com.example.jira.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="workflow")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Workflow extends Parent {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="issue_id")
    private Issue issue;

    @Column(name="description")
    private String description;

    @Column(name="steps")
    private String steps;

    public Workflow(){

    }

    public Workflow(Issue issue, String name, String description, String steps) {
        this.issue = issue;
        this.description = description;
        this.steps = steps;
    }


    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }


}
