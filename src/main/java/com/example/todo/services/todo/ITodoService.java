package com.example.todo.services.todo;

import com.example.todo.model.Todo;
import com.example.todo.requests.todo.AddTodoRequest;
import com.example.todo.requests.todo.UpdateTodoRequest;

import java.util.List;

public interface ITodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    Todo createTodo(AddTodoRequest addTodoRequest);
    Todo updateTodo(Long id,UpdateTodoRequest updateTodoRequest);
    void deleteTodo(Long id);
//    Todo getTodoByCategory();
}
