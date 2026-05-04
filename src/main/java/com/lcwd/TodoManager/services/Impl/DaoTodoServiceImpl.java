package com.lcwd.TodoManager.services.Impl;

import com.lcwd.TodoManager.dao.TodoDao;
import com.lcwd.TodoManager.models.Todo;
import com.lcwd.TodoManager.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class DaoTodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;
    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoDao.getAllTodo();
    }

    @Override
    public Todo getSingleTodo(int todoId) {
        return todoDao.getSingleTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo todo) {
        return todoDao.updateTodo(todoId,todo);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
