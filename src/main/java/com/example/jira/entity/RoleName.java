package com.example.jira.entity;

public enum RoleName {
    ADMIN("Admin"),
    MANAGER("Manager"),
    DEVELOPER("Developer");


    private String roleName;

    private RoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLabel() {
        return roleName;
    }
}