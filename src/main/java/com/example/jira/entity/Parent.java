package com.example.jira.entity;

import jakarta.persistence.*;

@MappedSuperclass //used when we use the concept of ineheritance in JPA
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
