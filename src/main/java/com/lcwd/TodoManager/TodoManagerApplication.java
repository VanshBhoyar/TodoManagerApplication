package com.lcwd.TodoManager;

import com.lcwd.TodoManager.dao.TodoDao;
import com.lcwd.TodoManager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {
	Logger logger = LoggerFactory.getLogger(TodoManagerApplication.class);

	@Autowired
	private TodoDao todoDao;

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("Application Started: ");
//		JdbcTemplate Template = todoDao.getJdbcTemplate();
//		logger.info("Templet : {}",Template);

//		Todo todo = new Todo();
//		todo.setId(2);
//		todo.setTitle("This is Java Course");
//		todo.setContent("All about Java");
//		todo.setStatus("Done");
//		todo.setAddedDate(new Date());
//		todo.setTodoDate(new Date());
//
//		todoDao.saveTodo(todo);

//		Todo singleTodo = todoDao.getSingleTodo(4);
//		logger.info("Todo : {}",singleTodo);
//		singleTodo.setTitle("Java Spring Boot");
//		singleTodo.setContent("Advanced Spring Boot");
//		singleTodo.setStatus("DONE");
//		singleTodo.setAddedDate(new Date());
//		singleTodo.setTodoDate(new Date());
//		todoDao.updateTodo(1,singleTodo);


//		List<Todo> allTodo = todoDao.getAllTodo();
//		logger.info("AllTodos : {}",allTodo);

//		todoDao.deleteTodo(4);

//		todoDao.deleteMultiple(new int[]{1,2});

	}
}
