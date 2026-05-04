package com.lcwd.TodoManager.services.Impl;

import com.lcwd.TodoManager.dao.TodoRepository;
import com.lcwd.TodoManager.exceptions.ResourceNotFoundException;
import com.lcwd.TodoManager.models.Todo;
import com.lcwd.TodoManager.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
public class TodoJpaServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getSingleTodo(int todoId) {
        return todoRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo with given Id is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        Todo todo1 = todoRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo with given Id is not found", HttpStatus.NOT_FOUND));
        todo1.setTitle(todo.getTitle());
        todo1.setContent(todo.getContent());
        todo1.setStatus(todo.getStatus());
        todo1.setTodoDate(todo.getTodoDate());

        return todoRepository.save(todo1);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoRepository.deleteById(todoId);
    }
}
