package com.example.todo.requests.category;

import com.example.todo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCategoryRequest {
    String name;
    String description;

    public Category createCategory(){
        return Category.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }

}
