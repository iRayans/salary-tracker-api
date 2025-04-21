package com.rayan.salarytracker.service;

import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseInsertDTO;
import com.rayan.salarytracker.dto.recurringExpense.RecurringExpenseReadOnlyDTO;

import java.util.List;

public interface IRecurringExpenseService {
    List<RecurringExpenseReadOnlyDTO> findAll();
    RecurringExpenseReadOnlyDTO findById(Long id);
    RecurringExpenseReadOnlyDTO save(RecurringExpenseInsertDTO recurringExpenseInsertDTO);
}
