package com.example.todo.controller;


import com.example.todo.model.Category;
import com.example.todo.requests.category.AddCategoryRequest;
import com.example.todo.requests.category.UpdateCategoryRequest;
import com.example.todo.response.ApiResponse;
import com.example.todo.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category/")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("all-categories")
    public ResponseEntity<ApiResponse> getCategories() {
        try {
            List<Category> allCategories = categoryService.getAllCategory();
            return ResponseEntity.ok(new ApiResponse("All Categories", allCategories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }
    @GetMapping("category/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try {
            Category category = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category", category));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @PostMapping("create-category")
    public ResponseEntity<ApiResponse> createTodo(@RequestBody AddCategoryRequest addCategoryRequest){
        try{
            Category category = categoryService.createCategory(addCategoryRequest);
            return ResponseEntity.ok(new ApiResponse("Category Added", category));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @PutMapping("update-category/{id}")
    public ResponseEntity<ApiResponse> updateTodo(@RequestBody UpdateCategoryRequest updateTodoRequest, @PathVariable Long id){
        try{
            Category category = categoryService.updateCategory(id,updateTodoRequest);
            return ResponseEntity.ok(new ApiResponse("Category Updated", category));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }

    @DeleteMapping("delete-category/{id}")
    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Long id){
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(new ApiResponse("Category", "Deletion Success"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ApiResponse("server-error", null)
            );
        }
    }
}
