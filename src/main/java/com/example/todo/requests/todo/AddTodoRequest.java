package com.example.todo.requests.todo;

import com.example.todo.model.Category;
import com.example.todo.model.Todo;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddTodoRequest {
    private String title;
    private Boolean status;
    private Long categoryId;

    public Todo createTodo(){
        return Todo.builder()
                .title(this.title)
                .status(this.status)
                .build();
    }
}
