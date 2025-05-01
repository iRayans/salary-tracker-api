package com.rayan.salarytracker.service.impls;


import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.expense.ExpenseUpdateDTO;
import com.rayan.salarytracker.mapper.Mapper;
import com.rayan.salarytracker.model.Category;
import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.RecurringExpense;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.CategoryRepository;
import com.rayan.salarytracker.reposetory.ExpenseRepository;
import com.rayan.salarytracker.reposetory.RecurringExpenseRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import com.rayan.salarytracker.service.IExpenseService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class ExpenseService implements IExpenseService {
    @Inject
    ExpenseRepository expenseRepository;
    @Inject
    Mapper mapper;
    @Inject
    LoggedInUser loggedInUser;
    @Inject
    CategoryRepository categoryRepository;
    @Inject
    RecurringExpenseRepository recurringExpenseRepository;


    @Override
    public ExpenseReadOnlyDTO save(ExpenseInsertDTO expenseInsertDTO) {
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create expense: User not found"));

        Category category = categoryRepository.findUserCategory(expenseInsertDTO.getCategoryId(), currentUser.getId())
                .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create expense: Category not found"));

        Expense expense = mapper.mapToExpense(expenseInsertDTO);
        expense.setCategory(category);
        expense.setUser(currentUser);

        Long recurringExpenseId = expenseInsertDTO.getRecurringExpenseId();
        if (recurringExpenseId != null) {
            RecurringExpense recurringExpense = recurringExpenseRepository.findRecurringExpenseById(
                    recurringExpenseId, currentUser.getId())
                    .orElseThrow(() -> new EntityInvalidArgumentsException("Cannot create expense: Recurring expense not found"));

            if (recurringExpense == null) {
                throw new EntityInvalidArgumentsException("RecurringExpense not found");
            }
            expense.setRecurringSource(recurringExpense);
        }

        // Save and return
        expenseRepository.persist(expense);
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    @Override
    public List<ExpenseReadOnlyDTO> findAllExpenses() {
        Long userId = loggedInUser.getUserId();
        List<Expense> expenses = expenseRepository.getExpensesList(userId);
        return expenses.stream().map(mapper::mapToExpenseReadOnlyDTO).collect(Collectors.toList());
    }

    @Override
    public ExpenseReadOnlyDTO findExpenseById(Long id) {
        Long userId = loggedInUser.getUserId();
        Expense expense = expenseRepository.findExpenseById(id,userId);
        if (expense == null) {
            throw new EntityInvalidArgumentsException("Expense not found");
        }
        return mapper.mapToExpenseReadOnlyDTO(expense);
    }

    @Override
    public ExpenseReadOnlyDTO updateExpense(Long expenseId, ExpenseUpdateDTO expenseUpdateDTO) {
        Long userId = loggedInUser.getUserId();
        Expense existExpense = expenseRepository.findExpenseById(expenseId, userId);
        if (existExpense == null) {
            throw new EntityInvalidArgumentsException("Expense not found");
        }
        fillFields(existExpense,expenseUpdateDTO);

        return mapper.mapToExpenseReadOnlyDTO(existExpense);
    }

    @Override
    public void deleteExpense(Long expenseId) {
    Long userId = loggedInUser.getUserId();
    Expense expense = expenseRepository.findExpenseById(expenseId, userId);
    if (expense == null) {
        throw new EntityInvalidArgumentsException("Expense not found");
    }
    expenseRepository.delete(expense);
    }

    public List<ExpenseReadOnlyDTO> findExpenseByMonth(YearMonth yearMonth) {
        Long userId = loggedInUser.getUserId();
        List<Expense> expense = expenseRepository.findExpenseByMonth(yearMonth,userId);
        return expense.stream().map(mapper::mapToExpenseReadOnlyDTO).collect(Collectors.toList());
    }

    public boolean existsByRecurringSource(Long recurringSourceId, Long userId) {
        return expenseRepository.existsByRecurringSource(recurringSourceId,userId);
    }

    private void fillFields(Expense existExpense, ExpenseUpdateDTO expenseUpdateDTO) {
        existExpense.setName(expenseUpdateDTO.getName() != null ? expenseUpdateDTO.getName() : existExpense.getName());
        existExpense.setDescription(expenseUpdateDTO.getDescription() != null ? expenseUpdateDTO.getDescription() : existExpense.getDescription());
        existExpense.setAmount(expenseUpdateDTO.getAmount() != null ? expenseUpdateDTO.getAmount() : existExpense.getAmount());
        existExpense.setPaid(expenseUpdateDTO.getPaid() != null ? expenseUpdateDTO.getPaid() : existExpense.isPaid());
    }
}
