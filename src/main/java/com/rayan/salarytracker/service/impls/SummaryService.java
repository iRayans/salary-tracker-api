package com.rayan.salarytracker.service.impls;

import com.rayan.salarytracker.dto.MonthlySummaryDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@ApplicationScoped
public class SummaryService {

    @Inject
    ExpenseService expenseService;

    @Inject
    BaseSalaryService baseSalaryService;

    public MonthlySummaryDTO getMonthlySummary(YearMonth yearMonth) {
        // Get expenses for the specific month only, not all expenses
        List<ExpenseReadOnlyDTO> expenses = expenseService.findExpenseByMonth(yearMonth);

        // Get active salary (consider adding error handling if no active salary exists)
        BigDecimal salary = baseSalaryService.getActiveSalary().getAmount();

        // Calculate total expenses
        BigDecimal totalExpenses = getTotalExpenses(expenses);
        // Calculate remaining amount
        BigDecimal remaining = salary.subtract(totalExpenses);

        // Build and return summary in one step
        return new MonthlySummaryDTO(
                totalExpenses,
                salary,
                yearMonth.toString(),
                remaining
        );
    }

    private BigDecimal getTotalExpenses(List<ExpenseReadOnlyDTO> expenses) {
        return expenses.stream()
                .map(ExpenseReadOnlyDTO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
