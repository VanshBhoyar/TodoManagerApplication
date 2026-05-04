package com.lcwd.TodoManager.services;

import com.lcwd.TodoManager.models.Todo;

import java.util.List;

public interface TodoService {
    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodo();
    public Todo getSingleTodo(int todoId);
    public Todo updateTodo(int todoId, Todo todo);
    public void deleteTodo(int todoId);
}
