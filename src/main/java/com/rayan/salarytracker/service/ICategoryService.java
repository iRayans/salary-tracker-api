package com.rayan.salarytracker.service;

import com.rayan.salarytracker.model.Category;

import java.util.List;

public interface ICategoryService {
    Category save(Category category);
    List<Category> findAll();
    Category findById(Long id);
    Category update(Long categoryId, Category category);
    void delete(Long categoryId);
}
