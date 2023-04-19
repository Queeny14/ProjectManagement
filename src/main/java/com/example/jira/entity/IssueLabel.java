package com.example.jira.entity;

public enum IssueLabel {
    EPIC("Epic"),
    BUGFIX("Bugfix"),
    STORY("Story"),
    TASK("Task");

    private String label;

    private IssueLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
