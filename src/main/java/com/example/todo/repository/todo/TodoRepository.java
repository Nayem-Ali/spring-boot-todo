package com.example.todo.repository.todo;

import com.example.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

//    @Query(value = "SELECT t FROM Todo t WHERE t.fk_todo_category = :categoryId")
    @Query(value = "SELECT * FROM todo WHERE fk_todo_category = :categoryId", nativeQuery = true)
    List<Todo> getAllTodosByCategory(@Param("categoryId") Long categoryId);
}
