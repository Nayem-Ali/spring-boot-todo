package com.example.todo.services;

import com.example.todo.model.Todo;
import com.example.todo.requests.AddTodoRequest;
import com.example.todo.requests.UpdateTodoRequest;

import java.util.List;

public interface ITodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    Todo createTodo(AddTodoRequest addTodoRequest);
    Todo updateTodo(Long id,UpdateTodoRequest updateTodoRequest);
    void deleteTodo(Long id);

}
