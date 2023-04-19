package com.example.jira.entity;

import jakarta.persistence.*;

@Entity
@Table(name="workflow")
public class Workflow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="issue_id")
    private Issue issue;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="steps")
    private String steps;

    public Workflow(){

    }

    public Workflow(Issue issue, String name, String description, String steps) {
        this.issue = issue;
        this.name = name;
        this.description = description;
        this.steps = steps;
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

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id=" + id +
                ", issue=" + issue +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", steps='" + steps + '\'' +
                '}';
    }
}
