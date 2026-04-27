package com.maminozolotce.spring_test.repository;

import com.maminozolotce.spring_test.entity.Task;
import com.maminozolotce.spring_test.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Modifying
    @Query("update Task set status = :status where id = :id")
    void update(int id, TaskStatus status);

    List<Task> findAllByStatus(TaskStatus status);
    List<Task> findAllByStatusAndTitleContainsOrderByStatusAsc(TaskStatus status, String part);
    int countByStatus(TaskStatus status);

}
