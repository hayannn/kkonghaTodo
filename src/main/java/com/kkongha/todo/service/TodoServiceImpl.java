package com.kkongha.todo.service;

import com.kkongha.todo.model.TodoEntity;
import com.kkongha.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoEntity> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public TodoEntity getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public TodoEntity saveOrUpdateTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
