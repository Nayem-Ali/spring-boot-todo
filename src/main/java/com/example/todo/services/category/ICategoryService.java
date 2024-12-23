package com.example.todo.services.category;

import com.example.todo.model.Category;
import com.example.todo.requests.category.AddCategoryRequest;
import com.example.todo.requests.category.UpdateCategoryRequest;


import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    Category getCategoryById(Long id);
    Category createCategory(AddCategoryRequest addCategoryRequest);
    Category updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest);
    void deleteCategory(Long id);
}
