package com.example.todo.exception;

import com.example.todo.repository.category.CategoryRepository;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
