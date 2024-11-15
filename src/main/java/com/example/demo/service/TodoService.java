package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper todoMapper;

    public void createTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    public Todo getTodoById(Integer id) {
        return todoMapper.selectTodoById(id);
    }

    public List<Todo> getTodosByPlanId(Integer planId) {
        return todoMapper.selectTodosByPlanId(planId);
    }

    public void updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
    }

    public void deleteTodo(Integer id) {
        todoMapper.deleteTodoById(id);
    }
}