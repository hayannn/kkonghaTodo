package com.kkongha.todo.service;

import com.kkongha.todo.model.TodoEntity;
import java.util.Date;
import java.util.List;

public interface TodoService {
    List<TodoEntity> getAllTodos();
    List<TodoEntity> getTodayTodos();
    TodoEntity getTodoById(Long id);
    TodoEntity saveOrUpdateTodo(TodoEntity todo);
    void deleteTodoById(Long id);
    void toggleCompletion(Long id);

    //성공률 계산 추가
    void calculateSuccessRate(List<TodoEntity> todos);

    List<TodoEntity> getTodosByDueDate(Date dueDate);
}
