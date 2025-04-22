package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.RecurringExpense;
import com.rayan.salarytracker.model.User;
import com.rayan.salarytracker.reposetory.ExpenseRepository;
import com.rayan.salarytracker.reposetory.RecurringExpenseRepository;
import com.rayan.salarytracker.security.LoggedInUser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ExpenseGenerationService {

    @Inject
    RecurringExpenseRepository recurringExpenseRepository;
    @Inject
    ExpenseRepository expenseRepository;
    @Inject
    LoggedInUser loggedInUser;

    /**
     * Manually generate expenses from recurring templates for a specific month
     */
    @Transactional
    public List<Expense> generateExpensesForMonth(YearMonth targetMonth) {
        // Get the current user
        User currentUser = loggedInUser.getUser()
                .orElseThrow(() -> new EntityInvalidArgumentsException("User not found"));

        // Get all active recurring expenses for this user
        List<RecurringExpense> activeTemplates = recurringExpenseRepository.findAcActiveByUserId(currentUser.getId());

        List<Expense> generatedExpenses = new ArrayList<>();

        // Generate expenses from each template
        for (RecurringExpense template : activeTemplates) {
            // Check if an expense from this template already exists for this month
            boolean exists = expenseRepository.existsByRecurringSourceAndYearMonth(
                    template, targetMonth);

            if (!exists) {
                // Create a new expense from the template
                Expense newExpense = new Expense();
                newExpense.setName(template.getName());
                newExpense.setDescription(template.getDescription());
                newExpense.setAmount(template.getAmount());
                newExpense.setCategory(template.getCategory());
                newExpense.setUser(currentUser);
                newExpense.setYearMonth(targetMonth);
                newExpense.setRecurringSource(template);
                newExpense.setPaid(false);

                // Save the new expense
                expenseRepository.persist(newExpense);
                generatedExpenses.add(newExpense);
            }
        }

        return generatedExpenses;
    }
}
