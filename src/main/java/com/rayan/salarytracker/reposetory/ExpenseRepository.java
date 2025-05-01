package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.Expense;
import com.rayan.salarytracker.model.RecurringExpense;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.YearMonth;
import java.util.List;

@ApplicationScoped
public class ExpenseRepository implements PanacheRepository<Expense> {

    public Expense findExpenseById(Long expenseId, Long userId) {
        return find("id = ?1 and user.id = ?2", expenseId, userId).firstResult();
    }

    public List<Expense> getExpensesList(Long userId) {
        return find("user.id", userId).list();
    }

    /**
     * Check if an expense already exists for a specific recurring expense template and month
     *
     * @param recurringSource The recurring expense template
     * @param yearMonth The year and month to check
     * @return true if an expense already exists, false otherwise
     */
    public boolean existsByRecurringSourceAndYearMonth(RecurringExpense recurringSource, YearMonth yearMonth) {
        return count("recurringSource.id = ?1 and yearMonth = ?2",
                recurringSource.getId(), yearMonth) > 0;
    }

    public List<Expense> findExpenseByMonth(YearMonth yearMonth, Long userId) {
        return find("user.id = ?1 and yearMonth = ?2", userId,yearMonth).list();
    }

    public boolean existsByRecurringSource(Long recurringId, Long userId) {
        return count("recurringSource.id = ?1 AND user.id = ?2", recurringId, userId) > 0;
    }
}
