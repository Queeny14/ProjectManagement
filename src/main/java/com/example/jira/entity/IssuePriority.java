package com.example.jira.entity;

public enum IssuePriority {
    CRITICAL("Critical"),
    MAJOR("Major"),
    MINOR("Minor");

    private final String priority;

    IssuePriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }
}
