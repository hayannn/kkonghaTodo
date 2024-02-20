package com.kkongha.todo.controller;

import com.kkongha.todo.model.TodoEntity;
import com.kkongha.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String getAllTodos(Model model) {
        List<TodoEntity> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @GetMapping("/add")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new TodoEntity());
        return "add-todo";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute TodoEntity todo) {
        todoService.saveOrUpdateTodo(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model) {
        TodoEntity todo = todoService.getTodoById(id);
        model.addAttribute("todo", todo);
        return "edit-todo";
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, @ModelAttribute TodoEntity updatedTodo) {
        updatedTodo.setId(id);
        todoService.saveOrUpdateTodo(updatedTodo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return "redirect:/todos";
    }
}
