package com.maminozolotce.spring_test.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "records")

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    private TaskStatus status;

    public Task() {
    }

    public Task(String title){
        this.title = title;
        this.status = TaskStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
