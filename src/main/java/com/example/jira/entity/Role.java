package com.example.jira.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends Parent{

    @Enumerated(EnumType.STRING)
    @Column(name="role_name")
    private RoleName name;


    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }


    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}