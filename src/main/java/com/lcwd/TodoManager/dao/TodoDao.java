package com.lcwd.TodoManager.dao;

import com.lcwd.TodoManager.helper.Helper;
import com.lcwd.TodoManager.models.Todo;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger logger= LoggerFactory.getLogger(TodoDao.class);

    private JdbcTemplate jdbcTemplate;

    public TodoDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        String createTable="CREATE TABLE IF NOT EXISTS todos(id int PRIMARY KEY, title varchar(255) not null,content varchar(255),status varchar(20) not null, addedDate datetime,todoDate datetime)";
        int update = jdbcTemplate.update(createTable);
        logger.info("Table Created : {}",update);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo saveTodo(Todo todo){
        String insertQuery = "INSERT INTO todos(id,title,content,status,addedDate,todoDate) VALUES(?,?,?,?,?,?)";

        int rows = jdbcTemplate.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());
        logger.info("JDBC Operation : {} Inserted",rows);
        return todo;
    }

    public Todo getSingleTodo(int id){
        String query = "SELECT * FROM todos WHERE id=?";
        Todo todo = jdbcTemplate.queryForObject(query, new TodoRowMapper(), id);
        logger.info("Todo : {}",todo);

//        Todo todo = new Todo();
//        todo.setId((int)todoData.get("id"));
//        todo.setTitle((String)todoData.get("title"));
//        todo.setContent((String)todoData.get("content"));
//        todo.setStatus((String)todoData.get("status"));
//        todo.setAddedDate(Helper.parseDate((LocalDateTime)todoData.get("addedDate")));
//        todo.setTodoDate(Helper.parseDate((LocalDateTime)todoData.get("todoDate")));


        return todo;
    }

    public List<Todo> getAllTodo(){
        String query="SELECT * FROM todos";
        List<Todo> todos = jdbcTemplate.query(query, new TodoRowMapper());

        return todos;

//        List<Todo> todos = maps.stream().map((map) -> {
//            Todo todo = new Todo();
//            todo.setId((int) map.get("id"));
//            todo.setTitle((String) map.get("title"));
//            todo.setContent((String) map.get("content"));
//            todo.setStatus((String) map.get("status"));
//            todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
//            todo.setTodoDate(Helper.parseDate((LocalDateTime) map.get("todoDate")));
//
//
//            return todo;
//        }).collect(Collectors.toList());
//
//        return todos;
    }
    public Todo updateTodo(int id, Todo newTodo){
        String query="UPDATE todos SET title=?,content=?,status=?,addedDate=?,todoDate=? WHERE id=?";
        int update = jdbcTemplate.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getTodoDate(), id);
        logger.info("Update {}",update);
        newTodo.setId(id);
        return newTodo;
    }
    public void deleteTodo(int id){
        String query="DELETE FROM todos WHERE id=?";
        int update = jdbcTemplate.update(query, id);
        logger.info("DELETED {}",update);
    }

    public void deleteMultiple(int ids[]){
        String query = "DELETE FROM todos WHERE id=?";
        int[] ints = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });
        for (int i : ints){
            logger.info("DELETED {}",i);
        }
    }
}
