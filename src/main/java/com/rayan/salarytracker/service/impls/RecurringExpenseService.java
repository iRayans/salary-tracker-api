package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.dto.category.CategoryReadOnlyDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.RecurringExpense;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.CategoryRepository;
import com.rayan.salarytracker.reposetory.RecurringExpenseRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.IRecurringExpenseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
@Transactional
public class RecurringExpenseService implements IRecurringExpenseService {

    @Inject
    Mapper mapper;
    @Inject
    RecurringExpenseRepository recurringExpenseRepository;
    @Inject
    LoggedInUser loggedInUser;
    @Inject
    CategoryRepository categoryRepository;

    @Override
    public RecurringExpenseReadOnlyDTO save(RecurringExpenseInsertDTO recurringExpenseInsertDTO) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create salary: User not found"));
        Category category = categoryRepository.findById(recurringExpenseInsertDTO.getCategoryId());
        RecurringExpense recurringExpense = mapper.mapToRecurringExpense(recurringExpenseInsertDTO);
        recurringExpense.setCategory(category);
        recurringExpense.setUser(currentUser);
        recurringExpenseRepository.persist(recurringExpense);
        return mapper.mapToRecurringExpenseReadOnlyDTO(recurringExpense);
    }

    @Override
    public List<RecurringExpenseReadOnlyDTO> findAll() {
        return List.of();
    }

    @Override
    public RecurringExpenseReadOnlyDTO findById(Long id) {
        return null;
    }


}
