package com.kkongha.todo.controller;

import com.kkongha.todo.model.AddTodo;
import com.kkongha.todo.model.TodoEntity;
import com.kkongha.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String getAllTodos(@RequestParam(name = "dueDate", required = false) String dueDateString, Model model) {
        List<TodoEntity> todos;
        if (dueDateString != null) {
            try {
                Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                todos = todoService.getTodosByDueDate(dueDate);
                model.addAttribute("currentDate", dueDateString);
            } catch (ParseException e) {
                todos = todoService.getAllTodos();
            }
        } else {
            todos = todoService.getAllTodos();  // 날짜를 선택하지 않은 경우 모든 할 일 목록을 불러오도록 수정
            model.addAttribute("currentDate", "");  // 선택된 날짜가 없으므로 빈 문자열로 설정
        }

        // 각 투두 항목의 성공률 계산
        todoService.calculateSuccessRate(todos);

        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @GetMapping("/filter")
    public String filterTodos(@RequestParam(name = "dueDate", required = false) String dueDateString, Model model) {
        List<TodoEntity> todos;
        if (dueDateString != null) {
            try {
                Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                todos = todoService.getTodosByDueDate(dueDate);
                model.addAttribute("currentDate", dueDateString);
            } catch (ParseException e) {
                todos = todoService.getAllTodos();
            }
        } else {
            todos = todoService.getAllTodos();
        }

        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @GetMapping("/add")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new AddTodo());
        return "add-todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute @Valid AddTodo addTodo, BindingResult result, Model model) throws ParseException {
        if (result.hasErrors()) {
            return "add-todo";
        }

        TodoEntity todoEntity = addTodo.toEntity();
        todoService.saveOrUpdateTodo(todoEntity);
        model.addAttribute("success", true);
        return "redirect:/todos";
    }

    @GetMapping("/toggle-completion/{id}")
    public String toggleCompletion(@PathVariable Long id) {
        todoService.toggleCompletion(id);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model) {
        TodoEntity todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit-todo";
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, @ModelAttribute @Valid TodoEntity updatedTodo, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-todo";
        }

        try {
            Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(updatedTodo.getDueDateStr());
            updatedTodo.setDueDate(dueDate);
        } catch (ParseException e) {
            // 날짜 형식이 올바르지 않을 경우 처리
            e.printStackTrace();
        }
        updatedTodo.setId(id);
        todoService.saveOrUpdateTodo(updatedTodo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }


    @GetMapping("/today")
    public String getTodayTodos(Model model) {
        List<TodoEntity> todayTodos = todoService.getTodayTodos();
        model.addAttribute("todos", todayTodos);
        return "todo-list";
    }


}
