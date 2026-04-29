package com.maminozolotce.spring_test.service;

import com.maminozolotce.spring_test.entity.Task;
import com.maminozolotce.spring_test.repository.TaskRepository;
import com.maminozolotce.spring_test.entity.DTO.TaskContainerDto;
import com.maminozolotce.spring_test.entity.TaskStatus;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskContainerDto findAllRecords(String filterMode){
        List<Task> tasks = taskRepository.findAll(Sort.by(Sort.Direction.ASC, "status"));
        //List<Task> tasks = taskRepository.findAllByStatus(TaskStatus.DONE);

        int numberOfActiveRecords = taskRepository.countByStatus(TaskStatus.ACTIVE);
        int numberOfDoneRecords = taskRepository.countByStatus(TaskStatus.DONE);
        if(filterMode == null || filterMode.isBlank()){
            return new TaskContainerDto(tasks, numberOfDoneRecords , numberOfDoneRecords);
        }

        String filterModeInUpperCase = filterMode.toUpperCase();
        List<String> allowedFilterModes = Arrays.stream(TaskStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if(allowedFilterModes.contains(filterModeInUpperCase)){
              List<Task> filteredTasks = tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.valueOf(filterModeInUpperCase))
                        .collect(Collectors.toList());
              return new TaskContainerDto(filteredTasks, numberOfDoneRecords, numberOfActiveRecords);
        }else{
            return new TaskContainerDto(tasks, numberOfDoneRecords , numberOfActiveRecords);
        }
    }

    public void saveRecord(String title){
         if(title != null && !title.isBlank()) {
        taskRepository.save(new Task(title.trim()));
        }
    }

    public void deleteRecord(int id){
        taskRepository.deleteById(id);
    }

    public void makeRecordDone(int id, TaskStatus status){
       taskRepository.update(id, status);
    }
}
