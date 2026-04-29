package com.maminozolotce.spring_test.entity.DTO;

import com.maminozolotce.spring_test.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
public class TaskContainerDto {

    private final List<Task> tasks;
    private final int numberOfDoneRecords;
    private final int numberOfActiveRecords;

}
