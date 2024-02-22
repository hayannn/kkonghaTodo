package com.kkongha.todo.service;

import com.kkongha.todo.model.TodoEntity;
import com.kkongha.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    @Override
    public List<TodoEntity> getTodayTodos() {
        Date today = new Date();

        // 날짜 부분을 0시 0분 0초로 설정
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        today = calendar.getTime();

        return todoRepository.findByDueDate(today);
    }

    @Override
    public List<TodoEntity> getTodosByDueDate(Date dueDate) {
        return todoRepository.findByDueDate(dueDate);
    }
}
