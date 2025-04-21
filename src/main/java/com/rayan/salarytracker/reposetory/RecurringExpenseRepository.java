package com.rayan.salarytracker.reposetory;

import com.rayan.salarytracker.model.RecurringExpense;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Month;
import java.util.List;

@ApplicationScoped
public class RecurringExpenseRepository implements PanacheRepository<RecurringExpense> {
    public List<RecurringExpense> findRecurringExpenses(Long userId) {
        return find("user.id = ?1", userId).list();
    }
    public RecurringExpense findRecurringExpenseById(Long recurringExpenseId, Long userId) {
        return find("id =?1 and user.id = ?2",recurringExpenseId,userId).firstResultOptional().orElse(null);
    }
}
