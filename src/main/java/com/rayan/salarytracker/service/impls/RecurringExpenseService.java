package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseUpdateDTO;
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
import java.util.stream.Collectors;

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
        Long userId = loggedInUser.getUserId();
        List<RecurringExpense> recurringExpenses = recurringExpenseRepository.findRecurringExpenses(userId);
        if (recurringExpenses.isEmpty()) {
            throw new EntityNotFoundException("Recurring expense not found");
        }
        return recurringExpenses.stream().map(mapper::mapToRecurringExpenseReadOnlyDTO).collect(Collectors.toList());
    }

    @Override
    public RecurringExpenseReadOnlyDTO findById(Long id) {
        Long userId = loggedInUser.getUserId();
        RecurringExpense recurringExpense = recurringExpenseRepository.findRecurringExpenseById(id,userId);
        if (recurringExpense == null) {
            throw new EntityNotFoundException("Recurring expense not found");
        }
        return mapper.mapToRecurringExpenseReadOnlyDTO(recurringExpense);

    }

    @Override
    public RecurringExpenseReadOnlyDTO update(Long id,RecurringExpenseUpdateDTO recurringExpenseUpdateDTO) {
        Long userId = loggedInUser.getUserId();
        RecurringExpense existingExpense = recurringExpenseRepository.findRecurringExpenseById(id,userId);
        if (existingExpense == null) {
            throw new EntityNotFoundException("Recurring expense not found");
        }
        updateFields(existingExpense, recurringExpenseUpdateDTO);

        return mapper.mapToRecurringExpenseReadOnlyDTO(existingExpense);
    }

    @Override
    public void deleteById(Long id) {
    Long userId = loggedInUser.getUserId();
    RecurringExpense recurringExpense = recurringExpenseRepository.findRecurringExpenseById(id,userId);
    if (recurringExpense == null) {
        throw new EntityNotFoundException("Recurring expense not found");
    }
    recurringExpenseRepository.delete(recurringExpense);
    }

    private void updateFields(RecurringExpense existExpense, RecurringExpenseUpdateDTO updateDTO){
        existExpense.setName(updateDTO.getName() != null ? updateDTO.getName() : existExpense.getName());
        existExpense.setDescription(updateDTO.getDescription() != null ? updateDTO.getDescription() : existExpense.getDescription());
        existExpense.setAmount(updateDTO.getAmount() != null ? updateDTO.getAmount() : existExpense.getAmount());
        existExpense.setDueDayOfMonth(updateDTO.getDueDayOfMonth() != null ? updateDTO.getDueDayOfMonth() : existExpense.getDueDayOfMonth());
        existExpense.setActive(updateDTO.isActive() != null ? updateDTO.isActive() : existExpense.isActive());
    }
}
