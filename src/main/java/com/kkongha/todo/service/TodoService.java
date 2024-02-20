package com.kkongha.todo.service;

import com.kkongha.todo.model.TodoEntity;
import java.util.List;

public interface TodoService {
    List<TodoEntity> getAllTodos();
    TodoEntity getTodoById(Long id);
    TodoEntity saveOrUpdateTodo(TodoEntity todo);
    void deleteTodoById(Long id);
}
