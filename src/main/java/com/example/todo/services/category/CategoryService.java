package com.example.todo.services.category;

import com.example.todo.exception.CategoryNotFoundException;
import com.example.todo.model.Category;
import com.example.todo.repository.category.CategoryRepository;
import com.example.todo.requests.category.AddCategoryRequest;
import com.example.todo.requests.category.UpdateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories =categoryRepository.findAll().stream().toList();

//        System.out.println(Arrays.toString(categories.toArray()));
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category is not found"));
    }

    @Override
    public Category createCategory(AddCategoryRequest addCategoryRequest) {
        Category newCategory = addCategoryRequest.createCategory();
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = getCategoryById(id);
        if(updateCategoryRequest.getName() != null){
            category.setName(updateCategoryRequest.getName());
        }

        if(updateCategoryRequest.getDescription()!= null){
            category.setDescription(updateCategoryRequest.getDescription());
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
