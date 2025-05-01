package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;
import com.rayan.salarytracker.dto.expense.ExpenseUpdateDTO;

import java.time.YearMonth;
import java.util.List;

public interface IExpenseService {
    ExpenseReadOnlyDTO save(ExpenseInsertDTO expenseInsertDTO);
    List<ExpenseReadOnlyDTO> findAllExpenses();
    ExpenseReadOnlyDTO findExpenseById(Long id);
    ExpenseReadOnlyDTO  updateExpense(Long expenseId,ExpenseUpdateDTO expenseUpdateDTO);
    void deleteExpense(Long expenseId);
    List<ExpenseReadOnlyDTO> findExpenseByMonth(YearMonth yearMonth);
}
