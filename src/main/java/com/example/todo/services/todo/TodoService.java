package com.example.todo.services.todo;

import com.example.todo.exception.TodoNotFoundException;
import com.example.todo.model.Todo;
import com.example.todo.repository.todo.TodoRepository;
import com.example.todo.requests.todo.AddTodoRequest;
import com.example.todo.requests.todo.UpdateTodoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodoService {

    final TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll().stream().toList();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo Not Found"));
    }

    @Override
    public Todo createTodo(AddTodoRequest addTodoRequest) {
        Todo newTodo = addTodoRequest.createTodo();
        return todoRepository.save(newTodo);
    }

    @Override
    public Todo updateTodo(Long id, UpdateTodoRequest updateTodoRequest) {
        Todo todo = getTodoById(id);
        if (updateTodoRequest.getTitle() != null) {
            todo.setTitle(updateTodoRequest.getTitle());
        }
        if (updateTodoRequest.getStatus() != null) {
            todo.setStatus(updateTodoRequest.getStatus());
        }
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = getTodoById(id);
        todoRepository.delete(todo);
    }
}
