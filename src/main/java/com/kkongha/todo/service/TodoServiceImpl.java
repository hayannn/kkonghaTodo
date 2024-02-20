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

    @Override
    public void toggleCompletion(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        // 완료 상태를 토글
        todo.setCompleted(!todo.isCompleted());

        // 업데이트된 투두 저장
        todoRepository.save(todo);
    }
}
