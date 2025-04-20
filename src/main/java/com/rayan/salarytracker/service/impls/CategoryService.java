package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.CategoryRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.ICategoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CategoryService implements ICategoryService {

    @Inject
    CategoryRepository categoryRepository;
    @Inject
    LoggedInUser loggedInUser;

    @Override
    public Category save(Category category) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create category: User not found"));
        category.setUser(currentUser);
        categoryRepository.persist(category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        Long userId = loggedInUser.getUserId();
        return categoryRepository.findCategories(userId);
    }

    @Override
    public Category findById(Long id) {
        Long userId = loggedInUser.getUserId();
        return categoryRepository.findUserCategory(id, userId);
    }

    @Override
    public Category update(Long categoryId,Category updatedCategory) {
        Long userId = loggedInUser.getUserId();
        Category existingCategory = categoryRepository.findUserCategory(categoryId,userId);
        if (existingCategory == null) {
            throw new EntityNotFoundException("Category record not found with the given ID for your account");
        }
        existingCategory.setName(updatedCategory.getName() != null ? updatedCategory.getName() : existingCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription() != null ? updatedCategory.getDescription() : existingCategory.getDescription());
        return existingCategory;
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
