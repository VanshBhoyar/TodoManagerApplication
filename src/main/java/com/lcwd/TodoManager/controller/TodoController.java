package com.lcwd.TodoManager.controller;

import com.lcwd.TodoManager.models.Todo;
import com.lcwd.TodoManager.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/todos")
public class TodoController {
    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;
    Random random = new Random();
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

//        String str=null;
//        logger.info("{}",str.length());
//        Integer.parseInt("3423oihd");


//        int id = random.nextInt(9999);
//        todo.setId(id);

        Date currentDate = new Date();
        todo.setAddedDate(currentDate);
        logger.info("Create Todo");
        logger.info("CurrentDate: {}",currentDate);
        logger.info("TodoDate: {}",todo.getTodoDate());
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> todos=todoService.getAllTodo();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        Todo todo2 = todoService.getSingleTodo(todoId);
        return ResponseEntity.ok(todo2);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId){
        Todo newTodo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(newTodo);
    }
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo successfully deleted");
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(Exception ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Exception : "+ex.getMessage());
//        return new ResponseEntity<>("Exception generated: "+ex.getMessage(),INTERNAL_SERVER_ERROR);
//    }
}
