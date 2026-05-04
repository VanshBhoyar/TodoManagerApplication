package com.lcwd.TodoManager.services.Impl;

import com.lcwd.TodoManager.exceptions.ResourceNotFoundException;
import com.lcwd.TodoManager.models.Todo;
import com.lcwd.TodoManager.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    List<Todo> todos = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("Todos {}",todos);
        return todo;
    }

    public List<Todo> getAllTodo() {
        return todos;
    }

    public Todo getSingleTodo(int todoId) {
        Todo todo = todos.stream().filter(t->todoId==t.getId()).findAny().orElseThrow(()-> new ResourceNotFoundException("Todo is not found with given ID", HttpStatus.NOT_FOUND));
        logger.info("Todo {}",todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo) {
        List<Todo> newUpdateList = todos.stream().map(t->{
            if(todoId==t.getId()){
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus(todo.getStatus());
                return t;
            }
            else{
                return t;
            }
        }).collect(Collectors.toList());

        todos=newUpdateList;
        todo.setId(todoId);
        return todo;
    }

    public void deleteTodo(int todoId){
        List<Todo> newList = todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
        todos=newList;
    }
}
