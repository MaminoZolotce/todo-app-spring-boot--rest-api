package com.maminozolotce.spring_test.entity.DTO;

import com.maminozolotce.spring_test.entity.Task;

import java.util.List;

public class TaskContainerDto {
    private final List<Task> tasks;
    private final int numberOfDoneRecords;
    private final int numberOfActiveRecords;

    public TaskContainerDto(List<Task> tasks, int numberOfDoneRecords, int numberOfActiveRecords) {
        this.tasks = tasks;
        this.numberOfDoneRecords = numberOfDoneRecords;
        this.numberOfActiveRecords = numberOfActiveRecords;
    }

    public List<Task> getRecords() {
        return tasks;
    }

    public int getNumberOfDoneRecords() {
        return numberOfDoneRecords;
    }

    public int getNumberOfActiveRecords() {
        return numberOfActiveRecords;
    }
}
