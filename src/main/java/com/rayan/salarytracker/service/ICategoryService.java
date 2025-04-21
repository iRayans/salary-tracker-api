package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.category.CategoryInsertDTO;
import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.model.Category;

import java.util.List;

public interface ICategoryService {
    CategoryReadOnlyDTO save(CategoryInsertDTO categoryInsertDTO);
    List<CategoryReadOnlyDTO> findAll();
    CategoryReadOnlyDTO findById(Long id);
    CategoryReadOnlyDTO update(Long categoryId, Category category);
    void delete(Long categoryId);
}
