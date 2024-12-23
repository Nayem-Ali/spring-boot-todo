package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.requests.AddTodoRequest;
import com.example.todo.requests.UpdateTodoRequest;
import com.example.todo.response.ApiResponse;
import com.example.todo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/todo/")
@RequiredArgsConstructor
public class TodoController {

    final TodoService todoService;

    @GetMapping("all-todos")
    public ResponseEntity<ApiResponse> getAllTodos() {
        try {
            List<Todo> allTodos = todoService.getAllTodos();
            return ResponseEntity.ok(new ApiResponse("All Todos", allTodos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }
    @GetMapping("todo/{id}")
    public ResponseEntity<ApiResponse> getTodoById(@PathVariable Long id){
        try {
            Todo todo = todoService.getTodoById(id);
            return ResponseEntity.ok(new ApiResponse("Todo", todo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @PostMapping("create-todo")
    public ResponseEntity<ApiResponse> createTodo(@RequestBody AddTodoRequest addTodoRequest){
        try{
            Todo todo = todoService.createTodo(addTodoRequest);
            return ResponseEntity.ok(new ApiResponse("Todo Added", todo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @PutMapping("update-todo/{id}")
    public ResponseEntity<ApiResponse> updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest, @PathVariable Long id){
        try{
            Todo todo = todoService.updateTodo(id, updateTodoRequest);
            return ResponseEntity.ok(new ApiResponse("Todo Updated", todo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @DeleteMapping("delete-todo/{id}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Long id){
        try {
            todoService.deleteTodo(id);
            return ResponseEntity.ok(new ApiResponse("Todo", "Deletion Success"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

}
