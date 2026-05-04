package com.lcwd.TodoManager.dao;

import com.lcwd.TodoManager.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

}
