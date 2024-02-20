package com.kkongha.todo.repository;

import com.kkongha.todo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByDueDate(Date dueDate);
}
