package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.category.CategoryInsertDTO;
import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.CategoryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.ICategoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class CategoryService implements ICategoryService {

    @Inject
    CategoryRepository categoryRepository;
    @Inject
    LoggedInUser loggedInUser;
    @Inject
    Mapper mapper;

    @Override
    public CategoryReadOnlyDTO save(CategoryInsertDTO categoryInsertDTO) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create category: User not found"));
        Category category = mapper.mapToCategory(categoryInsertDTO);
        category.setUser(currentUser);
        categoryRepository.persist(category);
        return mapper.mapToCategoryReadOnlyDTO(category);
    }

    @Override
    public List<CategoryReadOnlyDTO> findAll() {
        Long userId = loggedInUser.getUserId();
        List<Category> categories = categoryRepository.findCategories(userId);
        return categories.stream().map(category -> mapper.mapToCategoryReadOnlyDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryReadOnlyDTO findById(Long id) {
        Long userId = loggedInUser.getUserId();
        Category category = categoryRepository.findById(id);
        return mapper.mapToCategoryReadOnlyDTO(category);
    }

    @Override
    public CategoryReadOnlyDTO update(Long categoryId, Category updatedCategory) {
        Long userId = loggedInUser.getUserId();
        Category existingCategory = categoryRepository.findUserCategory(categoryId, userId);
        if (existingCategory == null) {
            throw new EntityNotFoundException("Category record not found with the given ID for your account");
        }
        existingCategory.setName(updatedCategory.getName() != null ? updatedCategory.getName() : existingCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription() != null ? updatedCategory.getDescription() : existingCategory.getDescription());
        return mapper.mapToCategoryReadOnlyDTO(existingCategory);
    }

    @Override
    public void delete(Long categoryId) {
        Long userId = loggedInUser.getUserId();
        Category category = categoryRepository.findUserCategory(categoryId, userId);
        if (category == null) {
            throw new EntityNotFoundException("Category record not found with the given ID for your account");
        }
        categoryRepository.delete(category);
    }
}
